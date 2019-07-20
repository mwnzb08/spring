package com.example.resttemplatedomore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder {
    private Long id;
    private String name;
    private List<Coffee> item;
    private OrderState state;
    private Date createTime;
    private Date updateTime;
}
