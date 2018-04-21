package com.jiangKlijna.web.service;

import com.jiangKlijna.web.bean.Result;

public interface UserService {

    Result regist(String username, String password);

    Result remove(int id);

    Result find(int id);
}
