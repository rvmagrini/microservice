package com.rvmagrini.microserviceorder.service;

import com.rvmagrini.microserviceorder.dto.InventoryResponse;
import com.rvmagrini.microserviceorder.dto.OrderRequest;
import com.rvmagrini.microserviceorder.model.LineItem;
import com.rvmagrini.microserviceorder.model.Order;
import com.rvmagrini.microserviceorder.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .code(UUID.randomUUID().toString())
                .lineItems(orderRequest.getLineItems().stream().map(item -> LineItem.builder()
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .skuCode(item.getSkuCode())
                        .build()).collect(Collectors.toList()))
                .build();

        List<String> skuCodes = order.getLineItems().stream().map(LineItem::getSkuCode).collect(Collectors.toList());

        // Unique Sync Request to Inventory Endpoint with all SkuCodes
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory/inventories", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            orderRepository.save(order);
            log.info("Order Placed (Code: " + order.getCode() + ")");
            return "Order Placed (Code: " + order.getCode() + ")";
        } else {
            log.warn("Order can not be placed: Product is not in stock.");
            throw new IllegalArgumentException("Order can not be placed: Product is not in stock.");
        }
    }

}
