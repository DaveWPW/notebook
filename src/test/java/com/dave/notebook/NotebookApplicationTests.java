package com.dave.notebook;

import com.dave.notebook.entity.Role;
import com.dave.notebook.entity.RoleMenu;
import com.dave.notebook.entity.User;
import com.dave.notebook.mapper.RoleMapper;
import com.dave.notebook.mapper.RoleMenuMapper;
import com.dave.notebook.mapper.UserMapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotebookApplication.class)
public class NotebookApplicationTests {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	@Test
	public void addUser(){
//		User user = new User();
//		user.setUsername("admin");
//		user.setRealname("Dave");
//		String password = "123456";
//		String salt = UUID.randomUUID().toString();
//		SimpleHash sHash = new SimpleHash("MD5", password, salt);
//		user.setPassword(sHash.toHex());
//		user.setPasswordSalt(salt);
//		user.setRoleId(1);
//		user.setStatus(1);
//		user.setCreateUser("admin");
//		user.setCreateTime(new Date());
//		user.setModifyUser("admin");
//		user.setModifyTime(new Date());
//		int insert = userMapper.insert(user);
//		System.out.println(insert);
//		System.out.println(user.getUserId());
	}

	@Test
	public void findUserAll(){
//		List<User> users = userMapper.selectAll();
//		for(User user : users){
//			System.out.println(user.getUsername());
//		}
	}

	@Test
	public void addRole(){
//		Role role = new Role();
//		role.setRoleName("admin");
//		role.setRoleNote("超级管理员");
//		role.setStatus(1);
//		role.setCreateUser("admin");
//		role.setCreateTime(new Date());
//		role.setModifyUser("admin");
//		role.setModifyTime(new Date());
//		int insert1 = roleMapper.insert(role);
//		System.out.println(insert1);
//		System.out.println(role.getRoleId());
//		for(int i = 0; i < 5; i++){
//			RoleMenu roleMenu = new RoleMenu();
//			roleMenu.setRoleId(role.getRoleId());
//			roleMenu.setMenuId(i+1);
//			int insert = roleMenuMapper.insert(roleMenu);
//			System.out.println(insert);
//			System.out.println(roleMenu.getRoleMenuId());
//		}
	}

}
