package com.example.utils;

import java.util.List;
import java.util.Map;

/**

 */
public class ObjectEmptyUtil {

    /**
     * 判断对象为空
     *
     * @param obj
     *            对象名
     * @return 是否为空
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
        {
            return true;
        }
        if ((obj instanceof List))
        {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String))
        {
            return ((String) obj).trim().equals("");
        }
        return false;
    }

    /**
     * 判断对象不为空
     *
     * @param obj
     *            对象名
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Object obj)
    {
        return !isEmpty(obj);
    }

    /**
     * 判断一个map中的必要参数是否为空
     * @param paramsMap
     * @param paramsStr  一个需要判断是否为空的带逗号的字符串，例如："result,userId,taskId,"
     * @return
     */
    public static boolean isAnyNotEmpty(Map<String, Object> paramsMap, String paramsStr){
        String[] paramsArr = paramsStr.split(",");

        for(int i = 0; i < paramsArr.length; i++){
            if(isEmpty(paramsMap.get(paramsArr[i]))){
                return false;
            }
        }

        return true;
    }
}
