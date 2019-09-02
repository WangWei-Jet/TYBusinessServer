package com.whty.example.service;

import java.util.List;

import com.whty.example.pojo.User;
import com.whty.example.pojo.UserExample;

public interface UsersService {

	// 查询用户列表
	List<User> selectByExample(UserExample example);
}
