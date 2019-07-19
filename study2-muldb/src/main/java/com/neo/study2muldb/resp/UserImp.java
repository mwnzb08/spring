package com.neo.study2muldb.resp;

import com.neo.study2muldb.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface UserImp {
    int save(User user, JdbcTemplate jdbcTemplate);
    void batchInsert(JdbcTemplate jdbcTemplate);
    List<User> selectAll(JdbcTemplate jdbcTemplate);
}
