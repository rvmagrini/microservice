package com.rvmagrini.microserviceorder.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {

    private String code;
    private List<LineItemDto> lineItems;

}
