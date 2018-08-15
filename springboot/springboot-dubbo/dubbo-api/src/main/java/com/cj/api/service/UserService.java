package com.cj.api.service;

import com.cj.api.bean.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    List<User> findByAll();

    void removeById(int id);

    User addUser(int id, String name);
}
