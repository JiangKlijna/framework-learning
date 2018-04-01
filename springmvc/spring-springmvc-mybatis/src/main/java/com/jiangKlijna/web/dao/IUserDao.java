package com.jiangKlijna.web.dao;

import com.jiangKlijna.web.bean.User;

public interface IUserDao {

    void save(User obj);

    void update(User obj);

    void delete(int id);

    User get(int id);

}
