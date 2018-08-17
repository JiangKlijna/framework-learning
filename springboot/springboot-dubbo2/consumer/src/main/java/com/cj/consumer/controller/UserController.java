package com.cj.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cj.api.bean.User;
import com.cj.api.common.Result;
import com.cj.api.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:19092")
    private UserService us;

    @RequestMapping("/find.do")
    public Result findById(int id) {
        User u = us.findById(id);
        return Result.success(u);
    }

    @RequestMapping("/find/all.do")
    public Result findByAll() {
        return Result.success(us.findByAll());
    }

    @RequestMapping("/remove.do")
    public Result removeById(int id) {
        us.removeById(id);
        return Result.success();
    }

    @RequestMapping("/add.do")
    public Result addUser(int id, String name) {
        User u = us.addUser(id, name);
        return Result.success(u);
    }

}
