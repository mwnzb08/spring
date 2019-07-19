package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee {
    private Long id;
    private String name;
    private int price;
    private Date createTime;
    private Date updateTime;
}
