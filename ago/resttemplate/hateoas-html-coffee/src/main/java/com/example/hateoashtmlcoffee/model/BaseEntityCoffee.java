package com.example.hateoashtmlcoffee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntityCoffee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    @CreationTimestamp
    private Date ct;
    @Column
    @UpdateTimestamp
    private Date ut;
}
