package com.example.resttemplatedomore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coffee {
    private Long id;
    private String name;
    private BigDecimal price;
    private Date createTime;
    private Date updateTime;
}
