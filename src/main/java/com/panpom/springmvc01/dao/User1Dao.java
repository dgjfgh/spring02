package com.panpom.springmvc01.dao;

import com.panpom.springmvc01.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by majianghua on 2018/1/3.
 */
@Repository
public interface User1Dao {

    void createTable (@Param("tableName") String tableName);
    void add(User user);
    void del(int id);
    void update(User user);
    User getUser(int id);
    User getUser1(@Param("Id") int id,@Param("Name")String name);
//    User[] list();
    ArrayList<User> list();
}
