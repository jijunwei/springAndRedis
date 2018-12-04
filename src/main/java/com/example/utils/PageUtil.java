package com.example.utils;

import com.github.pagehelper.PageHelper;

import java.util.Map;

public class PageUtil {
    /**
     * 分页工具
     * @param params
     */
    public static void pageHelper(Map<String, Object> params){
	String page=params.get("page").toString();
	String rows=params.get("rows").toString();
	//分页插件
	PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(rows));
    }
}
