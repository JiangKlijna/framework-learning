package com.jiangKlijna.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserControler {

	@RequestMapping("/regist")
	public String regist() {
		return "user regist";
	}

}
