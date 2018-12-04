package com.example.common;

import com.example.dao.entity.AuthUser;
import com.example.service.UserService;
import com.example.utils.Underline2Camel;
import com.google.gson.Gson;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseController {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService userService;
	/**
	 * 将对象转为json串
	 * @param obj
	 * @return
	 */
	protected String getJson(Object obj){
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
	/**
	 * 将json串转为对象
	 * @param json
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Object getObjOfJson(String json,Class clazz){
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}
	 HttpSession getSession() {
		HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return session;
	}


	 HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return attrs.getRequest();
	}
	/*public AuthUser getCurrentUser() {
		//第一种方法获取浏览器token
		String token = getRequest().getHeader("x-auth-token");

		String userId=getOnlineUserId(getRequest());
		String tokenServer=(String)getRequest().getSession().getAttribute("x-auth-token");
		if (org.apache.commons.lang3.StringUtils.isBlank(token)||!token.equals(tokenServer)) {
			return null;
		}
		AuthUser authUser = new AuthUser();
		//String xAuthToken = getToken();
		authUser.setToken(token);
		authUser.setUserId(userId);


		// 过期时间判断
		Date now = new Date();  // 当前的系统时间
        *//*Long timeDifference = new Long(authUser.getExpirationTime().longValue() - now.getTime());
        if(timeDifference.longValue() <= 0){
            return null;
        }*//*

		return authUser;
	}*/
	public AuthUser getCurrentUser() {

		//第二种方法生成Shrio token
		String userName=(String)getRequest().getSession().getAttribute("userName");
		String md5Pass=(String)getRequest().getSession().getAttribute("md5Pass");
		UsernamePasswordToken token = new UsernamePasswordToken(userName,md5Pass);
		String userId=getOnlineUserId(getRequest());
		UsernamePasswordToken tokenServer=(UsernamePasswordToken)getRequest().getSession().getAttribute("x-auth-token");
		if (token==null||token!=tokenServer) {
			return null;
		}
		AuthUser authUser = new AuthUser();
		//String xAuthToken = getToken();
		authUser.setToken(token);
		authUser.setUserId(userId);


		// 过期时间判断
		Date now = new Date();  // 当前的系统时间
        Long timeDifference = new Long(authUser.getExpirationTime().longValue() - now.getTime());
        if(timeDifference.longValue() <= 0){
            return null;
        }

		return authUser;
	}

	protected String getToken() {
		String token = "";

		Cookie[] cookies = getRequest().getCookies();

		if(cookies == null){
			if(getRequest().getHeader("x-auth-token") != null){
				token = getRequest().getHeader("x-auth-token");
				return token;
			}
			return token;
		}

		if (cookies.length > 1) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().contains("Authorization")) {
					token = cookie.getValue();
					break;
				}
			}
		}
		if(org.apache.commons.lang3.StringUtils.isBlank(token)){
			if(getRequest().getHeader("x-auth-token") != null){
				token = getRequest().getHeader("x-auth-token");
				return token;
			}
			return token;
		}
		return token;
	}
	/**
     * 跨域专用 =====打出json结果
     * @param response http response
     * @param callbackFunName 跨域回调方法名
     * @throws IOException 异常信息
     */
    protected  String renderJsonp(HttpServletResponse response , Object data,String callbackFunName) throws IOException {
        try {
        	String jsonResponse = "";
        	if(data instanceof String){
        		jsonResponse = (String)data;
        	} else {
        		jsonResponse = new Gson().toJson(data);
        	}
            logger.info("____Json Result:" + jsonResponse);
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String jsonStr="success_jsonpCallback"+ "("+jsonResponse+")";
            logger.info("____跨域输出"+jsonStr);
            response.getWriter().print(jsonStr);
            return jsonResponse;
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
    }

	/**
	 * 返回map形式参数
	 * @param request
	 * @return
	 */
	protected Map<String,Object> getParams(HttpServletRequest request){
		Object userId = request.getSession().getAttribute("userId");
		Object userName = request.getSession().getAttribute("userName");

		Map<String,Object> params = this.requestParamsToMap(request);
//        params.put("currenTime", DateUtil.getCurrentDateTime());
		//处理分页数据
		String page = params.get("page")==null?"1":params.get("page").toString();
		String rows =params.get("rows")==null?"20":params.get("rows").toString();
		Integer pageInt = Integer.valueOf(page);
		Integer rowsInt = Integer.valueOf(rows);
		params.put("offset", (pageInt - 1) * rowsInt);
		params.put("limit", rowsInt);
		//处理排序(逆向驼峰)
		String sort = params.get("sort")==null?"":params.get("sort").toString();
		String sortCase = Underline2Camel.camel2Underline(sort).toLowerCase();
		params.put("sort",sortCase);
		params.put("userId", userId);
		params.put("userName", userName);

		return params;
	}
	/**
	 * 把request值封装成Map
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String,Object> requestParamsToMap(HttpServletRequest request){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		Map<String, String[]> tmp =request.getParameterMap();
		if(tmp != null){
			for (String key : tmp.keySet()) {
				String[] values = tmp.get(key);
				String value = StringUtils.isEmpty(values[0].trim())?"":values[0].trim();
				paraMap.put(key,value);
			}
		}
		return paraMap;
	}

	protected String getOnlineUserId(HttpServletRequest request) {
		Object o = request.getSession().getAttribute("userId");
		String userId = "";
		if (o != null) {
			userId = o.toString();
		}
		return userId;
	}
	
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
	 
	 //生成n位随机数
	 public String generateRandomNumber(int n){
        if(n<1){
            throw new IllegalArgumentException("随机数位数必须大于0");
        }
        Double m=Math.random()*9*Math.pow(10, n-1)+Math.pow(10, n-1);
        return m.toString();
     }



	 
}
