package com.example.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;

/**
 * @author: wwf
 * @date: 2018/1/11
 * @description: 拼装xml报文
 */
public class SendHttpXmlUtil {
    protected static Logger logger = LoggerFactory.getLogger(SendHttpXmlUtil.class);

    /**
     * 拼装主体报文
     *
     * @param obj
     * @param sb
     * @return
     */
    public static StringBuffer buildXml(Object obj, StringBuffer sb) {
        if (obj instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) obj;
            for (Map.Entry<String, Object> m : map.entrySet()) {// body
                // 判断第一层是否为map
                if (m.getValue() instanceof Map) {
                    sb.append("<");
                    sb.append(m.getKey().toLowerCase());
                    sb.append(">");
                    @SuppressWarnings("unchecked")
                    Map<String, Object> maps = (Map<String, Object>) m.getValue();
                    for (Map.Entry<String, Object> s : maps.entrySet()) {
                        // 判断第二层是否为map list
                        if (s.getValue() instanceof List) {
                            // List<Object> map2 = getMap(s.getValue());
                            @SuppressWarnings("unchecked")
                            List<Map<String, Object>> listMap = (List<Map<String, Object>>) maps.get(s.getKey());
                            sb.append("<");
                            sb.append(s.getKey().toLowerCase());
                            sb.append(">");
                            // 循环集合
                            for (Map<String, Object> e : listMap) {
                                // 循环子节点
                                for (Map.Entry<String, Object> next : e.entrySet()) {
                                    sb.append("<");
                                    sb.append(next.getKey().toLowerCase());
                                    sb.append(">");
                                    if (e.get(next.getKey()) instanceof Map) {
                                        @SuppressWarnings("unchecked")
                                        Map<String, Object> nextMAP = (Map<String, Object>) e.get(next.getKey());
                                        for (Map.Entry<String, Object> n : nextMAP.entrySet()) {
                                            sb.append("<");
                                            sb.append(n.getKey().toLowerCase());
                                            sb.append(">");
                                            sb.append(n.getValue());
                                            sb.append("</");
                                            sb.append(n.getKey().toLowerCase());
                                            sb.append(">");
                                        }
                                    }
                                    sb.append("</");
                                    sb.append(next.getKey().toLowerCase());
                                    sb.append(">");
                                }
                            }
                            sb.append("</");
                            sb.append(s.getKey().toLowerCase());
                            sb.append(">");
                        } else if (s.getValue() instanceof Map) {
                            @SuppressWarnings("unchecked")
                            Map<String, Object> mp = (Map<String, Object>) s.getValue();
                            sb.append("<");
                            sb.append(s.getKey().toLowerCase());
                            sb.append(">");
                            // 循环子节点
                            for (Map.Entry<String, Object> next : mp.entrySet()) {
                                sb.append("<");
                                sb.append(next.getKey().toLowerCase());
                                sb.append(">");
                                sb.append(next.getValue());
                                sb.append("</");
                                sb.append(next.getKey().toLowerCase());
                                sb.append(">");
                            }
                            sb.append("</");
                            sb.append(s.getKey().toLowerCase());
                            sb.append(">");
                        } else {
                            sb.append("<");
                            sb.append(s.getKey().toLowerCase());
                            sb.append(">");
                            sb.append(s.getValue());
                            sb.append("</");
                            sb.append(s.getKey().toLowerCase());
                            sb.append(">");
                        }
                    }
                    // 闭标签
                    sb.append("</");
                    sb.append(m.getKey().toLowerCase());
                    sb.append(">");
                }
            }
        }
        return sb;
    }



    /**
     * 将map转化为XML格式的字符串
     *
     * @param resmap
     * @return
     */
    @SuppressWarnings("unchecked")
    public static StringBuffer requestBodyXml(Map<String, Object> resmap) {
        StringBuffer soapResultData = new StringBuffer();
        for (String key : resmap.keySet()) {
            if (resmap.get(key) instanceof List) {//判断是否为list
                List<Map<String,String>> list=(List<Map<String, String>>) resmap.get(key);
                for (Map<String,String> map:list) {
                    //list标签开始
                    soapResultData.append("<");
                    soapResultData.append(key.toLowerCase());
                    soapResultData.append(">");
                    //list 内容
                    for (String mapKey : map.keySet()) {
                        soapResultData.append("<");
                        soapResultData.append(mapKey.toLowerCase());
                        soapResultData.append(">");
                        soapResultData.append(map.get(mapKey));
                        soapResultData.append("</");
                        soapResultData.append(mapKey.toLowerCase());
                        soapResultData.append(">");
                    }
                    //list标签结束
                    soapResultData.append("</");
                    soapResultData.append(key.toLowerCase());
                    soapResultData.append(">");
                }
            }else{
                soapResultData.append("<");
                soapResultData.append(key.toLowerCase());
                soapResultData.append(">");
                soapResultData.append(resmap.get(key));
                soapResultData.append("</");
                soapResultData.append(key.toLowerCase());
                soapResultData.append(">");
            }
        }
        return soapResultData;
    }

    /**
     * 创建头信息
     * @param sb
     * @return
     */
    public static String buildHeadXml(StringBuffer sb){
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><transaction>")
                .append(sb).append("</transaction>");
        return xml.toString();
    }

    public static String buildBodyXml(StringBuffer sb){
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><transaction><body><gettx>")
                .append(sb).append("</gettx></body></transaction>");
        return xml.toString();
    }



    /**补齐长度
     * @param str
     * @return
     */
    public static String setLength2Xml(String str){
        String format = String.format( "%08d",str.length());
        return format+str;
    }





}
