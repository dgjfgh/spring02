package com.panpom.springmvc01.dao;

import java.util.ArrayList;

import com.panpom.springmvc01.po.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	
	@Select("SELECT * FROM t_user WHERE ID > #{id}")
	ArrayList<User> selectUser(int id);

}
