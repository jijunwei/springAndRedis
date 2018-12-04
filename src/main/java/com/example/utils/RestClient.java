package com.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * rest客户端
 *

 */
@Component
public class RestClient {
    final static Logger log = LoggerFactory.getLogger(RestClient.class);
    public static   Map<String,Object> restTemplate(String url,String jsonString){
        log.info("请求url：" + url + ",请求参数" + jsonString);
        // 核心返回结果报文字符串
        String returnJsonString = "";
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {

            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            // 设置超时
            requestFactory.setConnectTimeout(100000);
            requestFactory.setReadTimeout(100000);
            RestTemplate restTemplate = new RestTemplate(requestFactory);

            //设置HTTP请求头信息，实现编码等
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.set("Accept-Charset", "utf-8");
            //该值必须和文档中提供的参数格式一致(application/json)，编码统一为utf-8
            requestHeaders.set("Content-type", "application/json; charset=utf-8");// 设置编码
            //封装参数和请求头
            HttpEntity<String> entity = new HttpEntity<String>(jsonString, requestHeaders);
            //发送请求
            returnJsonString = restTemplate.postForObject(url, entity, String.class);
            //转码
            returnJsonString = new String(returnJsonString.getBytes("ISO-8859-1"), "utf-8");
            log.info("返回信息:" + returnJsonString);
//            returnMap = JsonUtil.jsonToMap(returnJsonString);
            Map map = GsonUtil.toBean(returnJsonString, Map.class);
            System.out.println(map);
            return map;
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String restTemplateJson(String url,String jsonString) {
        log.info("请求url：{},参数:{}", url, jsonString);
        // 核心返回结果报文字符串
        String returnJsonString;
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 设置超时
        requestFactory.setConnectTimeout(300000);
        requestFactory.setReadTimeout(300000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        //设置HTTP请求头信息，实现编码等
        HttpHeaders requestHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        requestHeaders.setContentType(type);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
        //封装参数和请求头
        HttpEntity<String> entity = new HttpEntity<String>(jsonString, requestHeaders);
        //发送请求
        returnJsonString = restTemplate.postForObject(url, entity, String.class);
        log.info("返回信息:{}", returnJsonString);
        return returnJsonString;
    }
}
