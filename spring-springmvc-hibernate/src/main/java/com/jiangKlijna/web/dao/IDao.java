package com.jiangKlijna.web.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao {
    void close();

    org.hibernate.Query query(String hql);

    org.hibernate.Criteria criteria(Class<?> arg0);

    org.hibernate.SQLQuery sqlQuery(String sql);

    void save(Object obj);

    void update(Object obj);

    void delete(Object obj);

    Object get(Class<?> entityClazz, Serializable id);

    long findCount(Class<?> entityClazz);

    List<?> find(String hql);

    List<?> find(String hql, Object... params);

    List<?> findByPage(String hql, int pageNo, int pageSize);

    List<?> findByPage(String hql, int pageNo, int pageSize, Object... params);
}
