package com.cj.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cj.api.bean.User;
import com.cj.api.service.UserService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class UserServiceImpl implements UserService {

    private static final List<User> us = new CopyOnWriteArrayList<>();

    static {
        us.add(new User(1, "u one"));
        us.add(new User(2, "u +-*"));
        us.add(new User(3, "u !@#"));
        us.add(new User(4, "u yui"));
        us.add(new User(5, "u 123"));
    }

    @Override
    public User findById(int id) {
        for (User u : us) if (u.getId() == id) return u;
        return null;
    }

    @Override
    public List<User> findByAll() {
        return us;
    }

    @Override
    public void removeById(int id) {
        us.remove(findById(id));
    }

    @Override
    public User addUser(int id, String name) {
        User u = new User(id, name);
        us.add(u);
        return u;
    }
}
