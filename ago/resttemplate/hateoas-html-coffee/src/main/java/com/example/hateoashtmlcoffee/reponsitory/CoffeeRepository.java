package com.example.hateoashtmlcoffee.reponsitory;

import com.example.hateoashtmlcoffee.model.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "/fruit")
public interface CoffeeRepository extends JpaRepository<Fruit,Long> {
    List<Fruit> findByNameInOrderById(List<String> list);
    Fruit findByName(String name);
}
