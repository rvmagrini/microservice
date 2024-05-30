package com.rvmagrini.microserviceproduct.service;

import com.rvmagrini.microserviceproduct.dto.ProductRequest;
import com.rvmagrini.microserviceproduct.dto.ProductResponse;
import com.rvmagrini.microserviceproduct.model.Product;
import com.rvmagrini.microserviceproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product Created: " + product.getName());
    }

    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        log.info("Total Products: " + products.size());
        return products.stream().map(product -> ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build()).collect(Collectors.toList());
    }

}
