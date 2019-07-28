package com.spring.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class NewOrderRequest {
    private String customer;
    private List<String> items;
}
