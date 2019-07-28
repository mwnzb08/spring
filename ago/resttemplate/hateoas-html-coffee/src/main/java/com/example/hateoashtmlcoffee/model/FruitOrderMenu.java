package com.example.hateoashtmlcoffee.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "t_order")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FruitOrderMenu extends BaseEntityCoffee implements Serializable {
    @Column(nullable = false)
    private String customer;
    @ManyToMany
    @JoinTable(name = "t_order_fruit")
    @OrderBy("id")
    @Column(nullable = false)
    private List<Fruit> list;

    @Enumerated
    @Column(nullable = false)
    private OrderMenuState states;
}
