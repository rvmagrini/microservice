package com.rvmagrini.microserviceorder.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "line_items")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}
