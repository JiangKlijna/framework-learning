package com.jiangKlijna.web.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PreDestroy;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jiangKlijna.web.app.ContextWrapper;

public class HibernateDao extends ContextWrapper implements IDao {

	private static SessionFactory factory;

	private static synchronized Session openSession(ContextWrapper wrapper) {
		synchronized (HibernateDao.class) {
			if (null == factory) {
				factory = wrapper.getApplicationContext().getBean("sessionFactory", SessionFactory.class);
			}
			return factory.openSession();
		}
	}

	private final Session sess;
	private final Transaction transaction;

	public HibernateDao() {
		sess = openSession(this);
		transaction = sess.beginTransaction();
	}

	// @PostConstruct
	// public void init() {
	// }

	@Override
	@PreDestroy
	public void close() {
		try {
			transaction.commit();
			sess.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public org.hibernate.Query query(String hql) {
		return sess.createQuery(hql);
	}

	@Override
	public org.hibernate.Criteria criteria(Class<?> arg0) {
		return sess.createCriteria(arg0);
	}
	
	@Override
	public org.hibernate.SQLQuery sqlQuery(String sql) {
		return sess.createSQLQuery(sql);
	}

	@Override
	public void save(Object obj) {
		sess.save(obj);
	}

	@Override
	public void update(Object obj) {
		sess.update(obj);
	}

	@Override
	public void delete(Object obj) {
		sess.delete(obj);
	}

	@Override
	public Object get(Class<?> entityClazz, Serializable id) {
		return sess.get(entityClazz, id);
	}

	@Override
	public long findCount(Class<?> entityClazz) {
		List<?> l = find("select count(c) from " + entityClazz.getSimpleName() + "as c");
		return (l != null && l.size() == 1) ? (Long) l.get(0) : 0;
	}

	@Override
	public List<?> find(String hql) {
		return sess.createQuery(hql).list();
	}

	@Override
	public List<?> find(String hql, Object... params) {
		Query query = sess.createQuery(hql);
		for (int i = 0, len = params.length; i < len; i++) {
			query.setParameter(i + "", params[i]);
		}
		return query.list();
	}

	@Override
	public List<?> findByPage(String hql, int pageNo, int pageSize) {
		return sess.createQuery(hql).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public List<?> findByPage(String hql, int pageNo, int pageSize, Object... params) {
		Query query = sess.createQuery(hql);
		for (int i = 0, len = params.length; i < len; i++) {
			query.setParameter(i + "", params[i]);
		}
		return query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
	}

}
