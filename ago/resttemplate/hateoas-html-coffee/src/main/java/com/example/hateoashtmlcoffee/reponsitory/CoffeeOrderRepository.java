package com.example.hateoashtmlcoffee.reponsitory;

import com.example.hateoashtmlcoffee.model.FruitOrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<FruitOrderMenu,Long> {
}
