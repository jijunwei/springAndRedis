package com.example.core.filter;

import org.apache.shiro.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SessionFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String url = httpServletRequest.getRequestURI();

        if (!url.contains("financialRegulation")&&!url.contains("overdue")&&!url.contains("user/login.do")&&!url.contains("/resources")&&!url.contains("FFQPayment/batchBackCallBack.do")&&!url.contains("toLogin.do")&&!url.contains("clearSession.do")&&!url.contains("/user/captcha.do") &&!url.contains("FFQPayment/repaymentSendMessage.do")&&!url.contains("FFQPayment/CICSinsertPaymentLog.do")&&!url.contains("repayment/CICSinsertRepaymentLog.do")&&!url.contains("/druid")) {
            if (!SecurityUtils.getSubject().isAuthenticated()) {
                //判断session里是否有用户信息
                if (httpServletRequest.getHeader("x-requested-with") != null
                        && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                    //如果是ajax请求响应头会有，x-requested-with,在响应头设置session状态
                    httpServletResponse.setHeader("sessionstatus", "timeout");
                }else {
                    ((HttpServletResponse) response).sendRedirect("/user/toLogin.do");
                }
            }
        }
        chain.doFilter(request, response);
    }


    public void destroy() {

    }

    @SuppressWarnings("unchecked")
    private static Map getParameterMapqqq(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }
}
