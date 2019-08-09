package com.mo.sss.controller.ValidRequest;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class JsonAdd {
    @NotNull
    private Long number;
    @NotNull
    private String name;
    @NotNull
    private Long age;
    @NotNull
    private String classes;
}
