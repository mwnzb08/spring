package com.neo.database.simple;


import com.neo.model.User;

import java.util.List;

public interface SimpleImp {
    int save(User user);
    int deleteById(long id);
    int update(User user);
    List<User> selectAll();
    User selectById(long id);
}
