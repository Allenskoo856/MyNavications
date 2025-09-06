/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nikati.manage.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nikati.manage.core.common.node.MenuNode;
import com.nikati.manage.modular.system.model.Category;
import com.nikati.manage.modular.system.service.IOperationLogService;
import com.nikati.manage.modular.system.service.impl.CategoryServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author jsnjfz
 * @Date 2019/7/25 21:23 首页控制器
 */
@Controller
public class IndexController extends BaseController {

	@Autowired
	private CategoryServiceImpl categoryService;

	/**
	 * 跳转到首页
	 */
	@RequestMapping("/")
	public String index(Model model) {
		List<MenuNode> menus = categoryService.getCatogryNode(new HashMap<>());
		List<MenuNode> titles = MenuNode.buildTitle(menus);
		List<Category> categorySiteList = categoryService.getCatogrySite(null);
		model.addAttribute("categorySiteList", categorySiteList);
		model.addAttribute("titles", titles);
		System.out.println(titles + "s");
		return "/index.html";
	}

	@RequestMapping("/search/{wd}")
	public String s(Model model, @PathVariable(value = "wd") String wd) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("title", wd);
		List<MenuNode> menus = categoryService.getCatogryNode(map);
		List<MenuNode> titles = MenuNode.buildTitle(menus);
		List<Category> categorySiteList = categoryService.getCatogrySiteByinfo(map);
		List<Category> resultList = new ArrayList<Category>();
		for (Category category : categorySiteList) {
			if (null != category.getSites() && category.getSites().size() != 0) {
				resultList.add(category);
			}
		}
		model.addAttribute("categorySiteList", resultList);
		model.addAttribute("titles", titles);
		System.out.println(titles);
		return "/index.html";
	}

	/**
	 * Ajax搜索接口 - 返回JSON数据
	 */
	@RequestMapping(value = "/api/search", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> apiSearch(@RequestParam(value = "keyword", required = false) String keyword) {
		Map<String, Object> result = new HashMap<>();
		
		System.out.println("接收到搜索请求，关键词: " + keyword);
		
		try {
			if (keyword == null || keyword.trim().isEmpty()) {
				// 如果搜索关键词为空，返回所有数据
				List<Category> categorySiteList = categoryService.getCatogrySite(null);
				result.put("success", true);
				result.put("data", categorySiteList);
				result.put("message", "获取全部数据成功");
				result.put("isSearch", false);
				System.out.println("返回全部数据，分类数量: " + (categorySiteList != null ? categorySiteList.size() : 0));
			} else {
				// 执行搜索
				HashMap<String, Object> map = new HashMap<>();
				map.put("title", keyword.trim());
				List<Category> categorySiteList = categoryService.getCatogrySiteByinfo(map);
				
				// 过滤出有搜索结果的分类
				List<Category> resultList = new ArrayList<>();
				for (Category category : categorySiteList) {
					if (null != category.getSites() && category.getSites().size() != 0) {
						resultList.add(category);
					}
				}
				
				result.put("success", true);
				result.put("data", resultList);
				result.put("message", "搜索完成，找到 " + resultList.size() + " 个分类");
				result.put("keyword", keyword);
				result.put("isSearch", true);
				System.out.println("搜索完成，关键词: " + keyword + ", 找到分类数量: " + resultList.size());
			}
		} catch (Exception e) {
			System.err.println("搜索API出错: " + e.getMessage());
			e.printStackTrace();
			result.put("success", false);
			result.put("message", "搜索出错: " + e.getMessage());
			result.put("data", new ArrayList<>());
		}
		
		System.out.println("返回结果: " + result.get("success") + ", " + result.get("message"));
		return result;
	}

	/**
	 * 测试API接口
	 */
	@RequestMapping(value = "/api/test", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> testApi() {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("message", "API正常工作");
		result.put("timestamp", System.currentTimeMillis());
		return result;
	}

	/**
	 * 跳转到关于页面
	 */
	@RequestMapping("/about")
	public String about(Model model) {
		return "/about.html";
	}

}
