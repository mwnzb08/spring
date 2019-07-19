package com.neo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffer {
    private Long id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
