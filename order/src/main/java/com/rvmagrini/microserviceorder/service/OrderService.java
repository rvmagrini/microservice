package com.rvmagrini.microserviceorder.service;

import com.rvmagrini.microserviceorder.dto.OrderRequest;
import com.rvmagrini.microserviceorder.model.LineItem;
import com.rvmagrini.microserviceorder.model.Order;
import com.rvmagrini.microserviceorder.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .code(UUID.randomUUID().toString())
                .lineItems(orderRequest.getLineItems().stream().map(item -> LineItem.builder()
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .skuCode(item.getSkuCode())
                        .build()).collect(Collectors.toList()))
                .build();

        orderRepository.save(order);
        log.info("Order Placed: " + order.getCode());
    }

}
