package com.dave.notebook.controller;

import com.dave.notebook.service.MenuService;
import common.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Menu控制层
 * 
 * @author Dave20191012
 *
 */
@RestController
@RequestMapping("/menu/")
public class MenuController {
	@Autowired
	private MenuService menuService;
	
	/**
     * 查询菜单的节点信息
     * 
     * @return
     */
    @RequestMapping("doFindMenuNodes")
	public JsonResult doFindMenuNodes(){
		 return new JsonResult(menuService.findMenuNodes());
	}
    
}
