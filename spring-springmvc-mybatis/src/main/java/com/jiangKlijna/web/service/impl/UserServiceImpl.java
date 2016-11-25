package com.jiangKlijna.web.service.impl;

import com.jiangKlijna.web.dao.IUserDao;
import org.springframework.stereotype.Service;

import com.jiangKlijna.web.bean.Result;
import com.jiangKlijna.web.bean.User;
import com.jiangKlijna.web.service.BaseService;
import com.jiangKlijna.web.service.UserService;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {

    @Resource
    private IUserDao userDao;

    @Override
    public Result regist(String username, String password) {
        try {
            userDao.save(new User(username, password));
            return sucessResult();
        } catch (Exception e) {
            return errorResult(e.toString());
        }
    }

    @Override
    public Result remove(int id) {
        try {
            userDao.delete(id);
            return sucessResult();
        } catch (Exception e) {
            return errorResult(e.toString());
        }
    }

    @Override
    public Result find(int id) {
        try {
            return sucessResult(userDao.get(id));
        } catch (Exception e) {
            return errorResult(e.toString());
        }
    }

}
