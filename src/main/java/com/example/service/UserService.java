package com.example.service;

import com.example.dao.entity.User;

public interface UserService {



	User checkLogin(String username, String password) throws Exception;


}
