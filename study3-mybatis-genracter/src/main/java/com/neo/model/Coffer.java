package com.neo.model;
import lombok.Data;

import java.util.Date;

@Data
public class Coffer {
    private Long id;
    private String name;
    private Long price;
    private Date createTime;
    private Date updateTime;
}
