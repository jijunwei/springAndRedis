package com.example.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

public class CustomRealm extends AuthorizingRealm{    
	
	/*@Autowired
	private UserService userService;*/
      

/*

     * 添加角色 
     * @param username 
     * @param info 
*/

    private void addRole(String username, SimpleAuthorizationInfo info) {
  
    }  
  


    private SimpleAuthorizationInfo addPermission(String username,SimpleAuthorizationInfo info) {
         return null;
    }    
    
      

/*
*
     * 获取授权信息 
*/

    protected AuthorizationInfo doGetAuthorizationInfo1(PrincipalCollection principals) {
        //用户名    
        String username = (String) principals.fromRealm(getName()).iterator().next();   
        //根据用户名来添加相应的权限和角色  
        if(!StringUtils.isEmpty(username)){  
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
            addPermission(username,info);  
            addRole(username, info);  
            return info;  
        }  
        return null;    
    }

  
/*
*
    * 登录验证  
*/


    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken ) throws AuthenticationException {    
        //令牌——基于用户名和密码的令牌    
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;    
        //令牌中可以取出用户名  
        String accountName = token.getUsername();  
        //让shiro框架去验证账号密码  
        if(!StringUtils.isEmpty(accountName)){
        	
        	/*User operator = userService.getUser(accountName);
        	
            return new SimpleAuthenticationInfo(operator.getUserName(), operator.getPassword(), getName()); */
        }  
          
        return null;  
    }

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}    
    
}    
