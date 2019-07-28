package com.spring.repository;

import com.spring.model.FruitOrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitOrderMenuRepository extends JpaRepository<FruitOrderMenu,Long> {
}
