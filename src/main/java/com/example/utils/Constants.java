package com.example.utils;

import com.google.gson.Gson;


public interface Constants {
    Gson GSON = new Gson();
    String PASSWORD_SALT = "_dx";
    String ZERO_FILL_TEMPLATE = "%04d";
    String FLOAT_MONEY_ZERO_FILL = "%.2f";
    String ROLE_ = "ROLE_";
    String PRE_TOKEN = "token_";
}
