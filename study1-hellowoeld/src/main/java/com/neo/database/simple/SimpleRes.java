package com.neo.database.simple;


import com.neo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class SimpleRes implements SimpleImp{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static AtomicLong atomicLong = new AtomicLong();
    @Override
    public int save(User user) {
        if (user.getId()==null){
            user.setId(atomicLong.incrementAndGet());
        }
        return jdbcTemplate.update("insert into test1 values(?,?,?)",user.getId(),user.getName(),user.getPassword());
    }

    @Override
    public int deleteById(long id) {
        return jdbcTemplate.update("delete from test1 where id=?",id);
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("update test1 set name=?,password=? where id=?",user.getName(),user.getPassword(),1);
    }

    @Override
    public List<User> selectAll() {
        return jdbcTemplate.query("select * from test1",new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public User selectById(long id) {
        return jdbcTemplate.queryForObject("select * from test1 where id = ?",new Object[]{id},new BeanPropertyRowMapper<User>(User.class));
    }
}
