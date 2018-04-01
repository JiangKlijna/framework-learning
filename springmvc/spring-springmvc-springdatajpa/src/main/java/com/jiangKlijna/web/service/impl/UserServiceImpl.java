package com.jiangKlijna.web.service.impl;

import com.jiangKlijna.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiangKlijna.web.bean.Result;
import com.jiangKlijna.web.bean.User;
import com.jiangKlijna.web.service.BaseService;
import com.jiangKlijna.web.service.UserService;

import javax.transaction.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserDao userDao;

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
            User u = userDao.getOne(id);
            return sucessResult(u.toString());
        } catch (Exception e) {
            return errorResult(e.toString());
        }
    }

}
