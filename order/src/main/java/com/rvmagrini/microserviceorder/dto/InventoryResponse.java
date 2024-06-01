package com.rvmagrini.microserviceorder.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryResponse {

    private String skuCode;
    private boolean isInStock;

}
