package com.example.controller.system;


import com.example.common.BaseController;
import com.example.dao.entity.User;
import com.example.service.UserService;
import com.example.utils.Json;
import com.example.utils.MD5Util;
import com.example.utils.ShiroUtils;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserControler extends BaseController {
	Logger logger = LoggerFactory.getLogger(super.getClass());
	

	@Autowired
	private UserService userService;



	
	@RequestMapping("toLogin")
	public String toLogin() {
		return "login/login";
	}
	@RequestMapping("toLoginout")
	public String toLoginout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "login/login";
	}
	@RequestMapping("toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = super.getParams(request);
		return new ModelAndView("login/index",params);
	}

	@RequestMapping("login")
	@ResponseBody
	public Json login(String userName, String password, String captcha, HttpServletRequest request)throws IOException {
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		Json json = new Json();
		if(!captcha.equalsIgnoreCase(kaptcha)){
			json.setMsg("验证码不正确!");
			return json;
		}
		User mUser = null;
		try{
			mUser = this.userService.checkLogin(userName,password);
			if(mUser!=null){
				MD5Util md5 = new MD5Util();
	            UsernamePasswordToken token = new UsernamePasswordToken(mUser.getUserName(),md5.md5Encode(password));
	            Subject currentUser = SecurityUtils.getSubject();  
	            if (!currentUser.isAuthenticated()){
	                //使用shiro来验证  
	                //token.setRememberMe(true);  
	                currentUser.login(token);//验证角色和权限  
	            } 
	            request.getSession().setAttribute("userId", mUser.getUserId());

				request.getSession().setAttribute("userName", mUser.getUserName());
				request.getSession().setAttribute("md5Pass", md5.md5Encode(password));
				request.getSession().setAttribute("x-auth-token",token);

		        
				json.setSuccess(true);
				json.setObj(mUser.getPasswordChanged());
			}else{
				json.setSuccess(false);
				json.setMsg("用户名密码不正确！");
			}
		}catch (UnknownAccountException e) {
			this.logger.error("************************errorLogger*************************", e);
		      json.setSuccess(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	


	
}
