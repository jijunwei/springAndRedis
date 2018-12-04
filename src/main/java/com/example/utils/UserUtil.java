package com.example.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public class UserUtil
{
    /**
     * 本方法实现将session中userId转成Long型
     * @param request
     * @return
     */
    public Long getSessionUserId(HttpServletRequest request ){

        Object userIdTemp = request.getSession().getAttribute("userId");
        Long userId  ;
        if(userIdTemp!=null&&!userIdTemp.equals("")){
            String userIdStr = String.valueOf(userIdTemp);
            userId = Long.parseLong(userIdStr);
        }else
            return 0L;

        return userId;
    }



    /**
     *
     * @param value(手机号key:phoneNum;身份证号key:cardId)
     * @return
     */
    public static Map<String,String> getDesensitization(Map<String,String> value){
        if(value.isEmpty()||(value.get("phoneNum").equals(null)&&value.get("cardId").equals(null)))
            return null;
        value.put("phoneNum",value.get("phoneNum").replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
        value.put("cardId",
                (value.get("cardId").length() ==15) ? value.get("cardId").replaceAll("(\\d{3})\\d{8}(\\w{4})","$1********$2"):value.get("cardId").replaceAll("(\\d{3})\\d{11}(\\w{4})","$1***********$2"));
        return value;
    }

    /**
     *银行卡号脱敏
     * @param
     * @return
     */
    public static String getDesensitizeBank(String value){
        if(value==null || value=="")
            return "";
        StringBuilder str = new StringBuilder(value);
        String str1 = "";
        for(int i=0;i<str.length()-7;i++){
            str1 += "*";
        }
        if(str.length()>7){
            str = str.replace(3,str.length()-4,str1);
        }

        return str.toString();
    }

    public static void main(String[] args){
        Map<String,String> map1 = new HashMap();
        map1.put("phoneNum","18311108259");
        map1.put("cardId","110101199901014230");
        Map<String,String> ppp = getDesensitization(map1);
        System.out.print(ppp);
    }


}
