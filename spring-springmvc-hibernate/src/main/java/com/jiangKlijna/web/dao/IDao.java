package com.jiangKlijna.web.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao {
	public void close();

	public org.hibernate.Query query(String hql);

	public org.hibernate.Criteria criteria(Class<?> arg0);

	public org.hibernate.SQLQuery sqlQuery(String sql);

	public void save(Object obj);

	public void update(Object obj);

	public void delete(Object obj);

	public Object get(Class<?> entityClazz, Serializable id);

	public long findCount(Class<?> entityClazz);

	public List<?> find(String hql);

	public List<?> find(String hql, Object... params);

	public List<?> findByPage(String hql, int pageNo, int pageSize);

	public List<?> findByPage(String hql, int pageNo, int pageSize, Object... params);
}
