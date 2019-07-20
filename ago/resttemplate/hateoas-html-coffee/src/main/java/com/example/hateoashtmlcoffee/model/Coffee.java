package com.example.hateoashtmlcoffee.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
@Entity
@Table(name = "t_coffee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Coffee extends BaseEntityCoffee implements Serializable {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Max(500)
    @Min(1)
    private BigDecimal price;
}
