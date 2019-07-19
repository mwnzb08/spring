package com.example.resttemplatedomore.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class NewOrderRequest {
    private String name;
    private List<String> item;
}
