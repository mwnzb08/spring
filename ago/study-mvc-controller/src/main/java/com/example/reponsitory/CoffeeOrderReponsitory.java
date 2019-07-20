package com.example.reponsitory;

import com.example.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CoffeeOrderReponsitory extends JpaRepository<CoffeeOrder,Long> {
    @Query(value = "select * from t_order where id=?1",nativeQuery = true)
    CoffeeOrder getOne(Long id);
}
