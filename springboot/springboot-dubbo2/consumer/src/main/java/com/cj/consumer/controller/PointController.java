package com.cj.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cj.api.bean.Point;
import com.cj.api.common.Result;
import com.cj.api.service.PointService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/point")
public class PointController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:19091")
    private PointService ps;

    @RequestMapping("/find.do")
    public Result findById(int id) {
        Point p = ps.findById(id);
        return Result.success(p);
    }

    @RequestMapping("/find/all.do")
    public Result findByAll() {
        return Result.success(ps.findByAll());
    }

    @RequestMapping("/remove.do")
    public Result removeById(int id) {
        ps.removeById(id);
        return Result.success();
    }

    @RequestMapping("/add.do")
    public Result addUser(int id, String name) {
        Point p = ps.addPoint(id, name);
        return Result.success(p);
    }

}
