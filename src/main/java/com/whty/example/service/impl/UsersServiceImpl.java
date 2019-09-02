package com.whty.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whty.example.mapper.UserMapper;
import com.whty.example.pojo.User;
import com.whty.example.pojo.UserExample;
import com.whty.example.service.UsersService;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

	@Autowired
	UserMapper usersMapper;

	@Override
	public List<User> selectByExample(UserExample example) {
		return usersMapper.selectByExample(example);
	}

}
