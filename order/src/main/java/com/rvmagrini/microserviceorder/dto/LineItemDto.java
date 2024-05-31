package com.rvmagrini.microserviceorder.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LineItemDto {

    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}
