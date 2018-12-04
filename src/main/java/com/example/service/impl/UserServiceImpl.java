package com.example.service.impl;

import com.example.dao.entity.User;
import com.example.dao.mapper.UserMapper;
import com.example.service.UserService;

import com.example.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
	private UserMapper userMapper;

	@Override
	public User checkLogin(String userName, String password) throws Exception {
		MD5Util md5Util = new MD5Util();
	    String passwordMD = md5Util.md5Encode(password);
	    User user = userMapper.getUserByUserName(userName, passwordMD);
	    return user;
	}

	
}
