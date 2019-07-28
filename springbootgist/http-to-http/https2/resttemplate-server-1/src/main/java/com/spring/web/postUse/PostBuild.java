package com.spring.web.postUse;

import lombok.Data;
import org.joda.money.Money;

import javax.validation.constraints.NotNull;

@Data
public class PostBuild {
    @NotNull
    private String name;
    @NotNull
    private Money price;
}
