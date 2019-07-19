package com.neo.study2muldb.resp;

import com.neo.study2muldb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRes implements UserImp {
    @Autowired
    private JdbcTemplate primaryTemplate;
//    @Autowired
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public int save(User user,JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null) {
            jdbcTemplate=primaryTemplate;
        }
        return jdbcTemplate.update("insert into user(name,password) values(?,?)", user.getName(), user.getPassword());
    }
    @Override
    public void batchInsert(JdbcTemplate jdbcTemplate){
        if (jdbcTemplate == null) {
            jdbcTemplate=primaryTemplate;
        }
        jdbcTemplate.batchUpdate("insert into user(name,password) values(?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        preparedStatement.setString(1,"mo-"+i);
                        preparedStatement.setString(2,"123456");
                    }

                    @Override
                    public int getBatchSize() {
                        return 5;
                    }
                });
//        List<User> list = new ArrayList<>();
//        list.add(new User("mo","123456"));
//        namedParameterJdbcTemplate.batchUpdate("insert into user(name,password) values(:name,:password)",
//                SqlParameterSourceUtils.createBatch(list));
//
    }

    @Override
    public List<User> selectAll(JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
            jdbcTemplate=primaryTemplate;
        }
        List<User> list =jdbcTemplate.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        return list;
    }
}
