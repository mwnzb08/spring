package com.spring.web.postUse;

import lombok.Data;

import java.util.List;

@Data
public class PostBuildOfOrder {
    private String customer;
    private List<String> list;
}
