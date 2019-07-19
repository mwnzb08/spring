package com.example.webclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coffee {
    private Long id;
    private String name;
    private BigDecimal price;
    private Date createTime;
    private Date updateTime;
}
