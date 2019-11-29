package com.dave.notebook.controller;

import com.dave.notebook.service.UserService;
import common.vo.JsonResult;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Login控制层
 * 
 * @author Dave20191010
 * 
 */
@RestController
@RequestMapping("/")
public class LoginController {
	private static Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public JsonResult doLogin(String username, String password) {
		if(StringUtils.isEmpty(username)) {
			return new JsonResult("登录名不能为空");
		}
		if(StringUtils.isEmpty(password)) {
			return new JsonResult("密码不能为空");
		}
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
			logger.info(username+" 登录成功"+new Date());
			return new JsonResult("Login Succeed", 1);
		} catch (UnknownAccountException e) {
			return new JsonResult("此用户不存在");
		} catch (IncorrectCredentialsException e) {
			return new JsonResult("密码错误");
		} catch (AuthenticationException e) {
			return new JsonResult("用户名密码错误");
		} catch (RuntimeException e) {
			logger.info("未知错误，请联系管理员");
			return new JsonResult("未知错误，请联系管理员");
		}
	}
}