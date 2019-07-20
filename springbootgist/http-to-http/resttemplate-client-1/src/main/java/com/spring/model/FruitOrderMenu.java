package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FruitOrderMenu implements Serializable {
    private String customer;
    private List<Fruit> OrderMenu;
    private OrderMenuStates orderMenuStates;
    private Date ct;
    private Date ut;
}
