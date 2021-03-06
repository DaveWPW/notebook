package com.dave.notebook.controller;

import com.dave.notebook.entity.User;
import com.dave.notebook.service.UserService;
import common.util.ShiroUtil;
import common.vo.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * User控制层
 * 
 * @author Dave20191011
 * 
 */
@RestController
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
    
    /**
     * 添加用户
     * 
     * @param user
     * @return
     */
    @RequestMapping("doAddUser")
    public JsonResult doAddUser(User user){
    	if(user == null) {
    		return new JsonResult("保存对象不能为空");
    	}
    	if(StringUtils.isEmpty(user.getUsername())) {
    		return new JsonResult("用户名称不能为空");
    	}
    	if(StringUtils.isEmpty(user.getRealname())) {
    		return new JsonResult("真实名不能为空");
    	}
    	if(StringUtils.isEmpty(user.getRoleId())) {
    		return new JsonResult("角色不能为空");
    	}
    	int count = userService.findUserByUserName(user.getUsername());
    	if(count > 0){
    		return new JsonResult("用户名已存在");
    	}
    	int rows = userService.addUser(user);
    	if(rows != 1) {
    		return new JsonResult("Add Failed!!");
    	}
    	return new JsonResult("Added Successfully!", rows);
    }
    
    /**
     * 查找查询所有用户
     * 
     * @param pageCurrent
     * @param username
     * @param staffId
     * @return
     */
    @RequestMapping("doFindUserList")
    public JsonResult doFindUserList(Integer pageCurrent, String username){
    	if(pageCurrent == null || pageCurrent <= 0) {
    		return new JsonResult("参数不合法");
    	}
    	return new JsonResult(userService.findUserList(pageCurrent, username));
    }
    
    /**
     * 根据用户ID获取用户
     * 
     * @param userId
     * @return
     */
    @RequestMapping("doFindUserById")
    public JsonResult doFindUserById(Integer userId){
    	if(StringUtils.isEmpty(userId)) {
    		return new JsonResult("用户ID不能为空!!");
    	}
    	User user = userService.findUserById(userId);
    	return new JsonResult(user);
    }
    
    /**
     * 修改用户
     * 
     * @param user
     * @return
     */
    @RequestMapping("doUpdateUser")
    public JsonResult doUpdateUser(User user){
    	if(user == null) {
    		return new JsonResult("保存对象不能为空!!");
    	}
    	String admin = "admin";
    	if(admin.equals(user.getUsername()) && user.getIsRestPassword() == 0) {
    		return new JsonResult("禁止修改admin用户!!");
    	}
    	if(admin.equals(user.getUsername()) && user.getRoleId() != 1) {
    		return new JsonResult("禁止修改admin用户!!");
    	}
    	if(StringUtils.isEmpty(user.getUserId())) {
    		return new JsonResult("用户ID不能为空");
    	}
    	if(StringUtils.isEmpty(user.getUsername())) {
    		return new JsonResult("用户名不能为空");
    	}
    	if(StringUtils.isEmpty(user.getRoleId())) {
    		return new JsonResult("角色ID不能为空");
    	}
    	int row = userService.updateUser(user);
    	if(row == 1) {
    		User currentUser = ShiroUtil.getCurrentUser();
    		String username = currentUser.getUsername();
    		if(username.equals(user.getUsername()) && user.getIsRestPassword() == 1) {
    			return new JsonResult("Updated Succeed!", 2);
    		}
    		return new JsonResult("Updated Successfully!", row);
    	}
    	return new JsonResult("Update Failed!!");
    }
    
    /**
     * 删除用户
     * 
     * @param userId
     * @return
     */
    @RequestMapping("doDeleteUser")
    public JsonResult doDeleteUser(Integer userId){
    	if(StringUtils.isEmpty(userId)) {
    		return new JsonResult("用户ID不能为空");
    	}
    	if(userId == 1) {
    		return new JsonResult("禁止删除admin用户!!");
    	}
    	User user = (User)SecurityUtils.getSubject().getPrincipal();
    	int id = user.getUserId();
    	if(id == userId) {
    		return new JsonResult("不能删除自身用户");
    	}
    	int rows = userService.deleteUser(userId);
		if(rows != 1) {
			return new JsonResult("Delete Failed!!");
		}
		return new JsonResult("Deleted Successfully!", rows);
    }
    
}