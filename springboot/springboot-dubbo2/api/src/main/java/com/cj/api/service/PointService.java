package com.cj.api.service;

import com.cj.api.bean.Point;

import java.util.List;

public interface PointService {

    Point findById(int id);

    List<Point> findByAll();

    void removeById(int id);

    Point addPoint(int id, String name);
}
