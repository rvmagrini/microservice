package com.rvmagrini.microserviceproduct.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;

}
