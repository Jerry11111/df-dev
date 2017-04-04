package com.df.dev.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestAction {
	
	// 调到ftl页面
	@RequestMapping("test_html")
	public String index_html(@ModelAttribute("model") ModelMap model){
		model.addAttribute("firstname", "test");
		model.addAttribute("lastname", "test");
		return "f_test";
	}

}
