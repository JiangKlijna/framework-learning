package com.jiangKlijna.web.service;

import com.jiangKlijna.web.bean.Result;

public interface UserService {

	public Result regist(String username, String password);

	public Result remove(int id, String username);
}
