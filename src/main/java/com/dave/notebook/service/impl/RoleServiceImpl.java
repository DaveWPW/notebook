package com.dave.notebook.service.impl;

import com.dave.notebook.entity.Role;
import com.dave.notebook.entity.RoleMenu;
import com.dave.notebook.entity.User;
import com.dave.notebook.mapper.RoleMapper;
import com.dave.notebook.mapper.RoleMenuMapper;
import com.dave.notebook.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import common.util.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.dave.common.vo.CheckBox;

/**
 * Role业务层接口实现类
 * 
 * @author Dave20191012
 */
@Transactional(rollbackFor=Throwable.class)
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
    private RoleMapper roleMapper;
	@Autowired
    private RoleMenuMapper roleMenuMapper;

	@Override
	public int findRoleByRoleName(String roleName) {
		Role role = new Role();
		role.setRoleName(roleName);
		return roleMapper.selectCount(role);
	}

	@Override
	public int addRole(Role role) {
		User user = ShiroUtil.getCurrentUser();
		role.setCreateUser(user.getUsername());
		role.setCreateTime(new Date());
		role.setModifyUser(user.getUsername());
		role.setModifyTime(new Date());
		role.setStatus(1);
		int row = roleMapper.insert(role);
		for(int menuId : role.getMenuIds()) {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRoleId(role.getRoleId());
			roleMenu.setMenuId(menuId);
			roleMenuMapper.insert(roleMenu);
		}
		return row;
	}

	@Override
	public PageInfo<Role>  findRoleList(int pageCurrent, String roleName) {
		PageHelper.startPage(pageCurrent, 10);
		PageInfo<Role> pageInfo = null;
		if(StringUtils.isEmpty(roleName)){
			Example example = new Example(Role.class);
			pageInfo = new PageInfo<>(roleMapper.selectByExample(example));
		}else{
			pageInfo = new PageInfo<>(roleMapper.findListByName(roleName));
		}
		return pageInfo;
	}

	@Override
	public Map<String, Object> findRoleById(int roleId) {
		Role role = roleMapper.selectByPrimaryKey(roleId);
		if(role == null) {
			return null;
		}
		List<Integer> menuIds = roleMenuMapper.findMenuIdsByRoleId(roleId);
		Map<String, Object> map = new HashMap<>();
		map.put("role", role);
		map.put("menuIds", menuIds);
		return map;
	}

	@Override
	public int updateRole(Role role) {
		User user = ShiroUtil.getCurrentUser();
		role.setModifyUser(user.getUsername());
		role.setModifyTime(new Date());
		role.setStatus(1);
		int row = roleMapper.updateByPrimaryKeySelective(role);

		RoleMenu roleMenuParams = new RoleMenu();
		roleMenuParams.setRoleId(role.getRoleId());
		roleMenuMapper.delete(roleMenuParams);

		for(int menuId : role.getMenuIds()) {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRoleId(role.getRoleId());
			roleMenu.setMenuId(menuId);
			roleMenuMapper.insert(roleMenu);
		}
		return row;
	}
	
	@Override
	public String deleteRole(int roleId) {
		List<String> users = roleMapper.findRoleUse(roleId);
		if(users.size() == 0) {
			int row = roleMapper.deleteByPrimaryKey(roleId);
			if(row != 1) {
				return "Delete Failed!!";
			}
			RoleMenu roleMenuParams = new RoleMenu();
			roleMenuParams.setRoleId(roleId);
			roleMenuMapper.delete(roleMenuParams);
		} else {
			return users.toString();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> findRoles() {
		return roleMapper.findRoles();
	}

}
