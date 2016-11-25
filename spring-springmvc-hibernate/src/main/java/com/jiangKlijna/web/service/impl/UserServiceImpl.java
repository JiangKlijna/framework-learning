package com.jiangKlijna.web.service.impl;

import org.springframework.stereotype.Service;

import com.jiangKlijna.web.bean.Result;
import com.jiangKlijna.web.bean.User;
import com.jiangKlijna.web.dao.IDao;
import com.jiangKlijna.web.service.BaseService;
import com.jiangKlijna.web.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {

    @Override
    public Result regist(String username, String password) {
        try {
            IDao dao = dao();
            dao.save(new User(username, password));
            return sucessResult();
        } catch (Exception e) {
            return errorResult(e.toString());
        }
    }

    @Override
    public Result remove(int id) {
        try {
            IDao dao = dao();
            dao.delete(new User(id, "", ""));
            return sucessResult();
        } catch (Exception e) {
            return errorResult(e.toString());
        }
    }

    @Override
    public Result find(int id) {
        try {
            return sucessResult(dao().get(User.class, id));
        } catch (Exception e) {
            return errorResult(e.toString());
        }
    }

}
