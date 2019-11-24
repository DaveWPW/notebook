package com.dave.notebook.controller;

import common.vo.JsonResult;
import com.dave.notebook.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Menu控制层
 * 
 * @author Dave20191012
 *
 */
@Controller
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
	@ResponseBody
	public JsonResult doFindMenuNodes(){
		 return new JsonResult(menuService.findMenuNodes());
	}
    
}
