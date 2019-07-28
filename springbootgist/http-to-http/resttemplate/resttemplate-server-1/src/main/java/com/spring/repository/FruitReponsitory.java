package com.spring.repository;

import com.spring.model.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(path = "/fruit")
public interface FruitReponsitory  extends JpaRepository<Fruit,Long> {
    @Query(value = "select * from t_fruit where name=?1",nativeQuery = true)
    Fruit getOneByName(String name);
    @Query(value = "select * from t_fruit where id=?1",nativeQuery = true)
    Fruit getOneById(Long id);
    List<Fruit> findByNameInOrderById(List<String> fruits);
}
