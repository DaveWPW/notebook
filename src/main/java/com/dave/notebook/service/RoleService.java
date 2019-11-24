package com.dave.notebook.service;

import com.dave.notebook.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Role业务层接口
 * 
 * @author Dave20191012
 *
 */
public interface RoleService {

	int findRoleByRoleName(String roleName);

	int addRole(Role role);

	PageInfo<Role> findRoleList(int pageCurrent, String roleName);
	
	Map<String, Object> findRoleById(int roleId);
	
	int updateRole(Role role);
	
	String deleteRole(int roleId);
	
	List<Map<String, Object>> findRoles();
	
}