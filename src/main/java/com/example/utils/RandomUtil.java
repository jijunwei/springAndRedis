package com.example.utils;

import java.text.SimpleDateFormat;

public class RandomUtil {
    public static void main(String[] args) {
        randomNumber("CS",1000);
    }
    /*
    * 生成带随机数的订单编号
    * 格式：前缀英文+年月日时分+n位随机数
    * previous：前缀英文
    * length：100 三位，1000四位。。。。。以此类推
    * */
    public static String randomNumber(String previous,int length){
        int random = (int) ((Math.random() * 9 + 1) * length);
        java.util.Date d = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String dateNowStr = sdf.format(d);
//        System.out.println("格式化后的日期：" + dateNowStr);
        String billId=previous+dateNowStr+random;
        System.out.println(billId);
        return billId;
    }
}
