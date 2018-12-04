package com.example.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

public class GsonUtil {
	public static <T> T toBean(String jsonStr,Class<T> beanClass){
		return JSON.parseObject(jsonStr,beanClass);
	}
	public static String toJSONString(Object object){
		return JSON.toJSONString(object);
	}
	public static String toJSON1String(Object object){
		String json = JSON.toJSONString(object,SerializerFeature.WriteMapNullValue);
		json=json.replace("null", "''");
		return json;
	}
	
	public static <T>List<T> toList(String jsonStr,Class<T> beanClass){
		
		return JSON.parseArray(jsonStr, beanClass);
	}
	
	/** 
	 * @Title: toJsonString 
	 * @Description: 处理集合不为空, 但集合里元素的值为空的情况。 解决办法是将集合置为空
	 * @param object
	 * @return  
	 * @author xiaohong.Wang 
	 * @date 2016年12月29日 下午3:33:29
	 */
	public static String handleJsonSwitch(Object object){
		return toJSONString(object).replace("[null]", "[]");
	}
		
}
