package com.example.reponsitory;

import com.example.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CoffeeReponsitory  extends JpaRepository<Coffee,Long> {
    List<Coffee> findByName(String name);
    Coffee findById(long id);
    @Transactional
    @Modifying//加上这两个注解。
    @Query(value = "delete from t_coffee where name=?1",nativeQuery = true)
    void deleteByName(String name);
    List<Coffee> findByNameInOrderById(List<String> list);
}
