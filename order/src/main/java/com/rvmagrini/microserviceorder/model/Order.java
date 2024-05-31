package com.rvmagrini.microserviceorder.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LineItem> lineItems;

}
