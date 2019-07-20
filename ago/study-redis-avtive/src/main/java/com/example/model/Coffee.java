package com.example.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Coffee {
    private Long id;
    private String name;
    private String price;
    private Date createTime;
    private Date updateTime;
}
