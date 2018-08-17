package com.cj.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cj.api.bean.Point;
import com.cj.api.service.PointService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class PointServiceImpl implements PointService {

    private static final List<Point> ps = new CopyOnWriteArrayList<>();

    static {
        ps.add(new Point(1, "p one"));
        ps.add(new Point(2, "p +-*"));
        ps.add(new Point(3, "p !@#"));
        ps.add(new Point(4, "p yui"));
        ps.add(new Point(5, "p 123"));
    }

    @Override
    public Point findById(int id) {
        for (Point u : ps) if (u.getId() == id) return u;
        return null;
    }

    @Override
    public List<Point> findByAll() {
        return ps;
    }

    @Override
    public void removeById(int id) {
        ps.remove(findById(id));
    }

    @Override
    public Point addPoint(int id, String name) {
        Point p = new Point(id, name);
        ps.add(p);
        return p;
    }
}
