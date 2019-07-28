package com.spring.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FruitOrderMenu extends BaseFruit implements Serializable {
    private String customer;
    @ManyToMany
    @JoinTable(name = "t_order_fruit")
    @OrderBy("id")
    private List<Fruit> list;
    @Enumerated
    @Column(nullable = false)
    private OrderStates states;
}
