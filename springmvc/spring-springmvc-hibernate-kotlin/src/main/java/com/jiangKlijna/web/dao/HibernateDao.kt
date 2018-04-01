package com.jiangKlijna.web.dao

import java.io.Serializable

import javax.annotation.PreDestroy

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction

import com.jiangKlijna.web.app.ContextWrapper

class HibernateDao : ContextWrapper(), IDao {

    private val sess: Session
    private val transaction: Transaction

    init {
        sess = openSession(this)
        transaction = sess.beginTransaction()
    }

    // @PostConstruct
    // public void init() {
    // }

    @PreDestroy
    override fun close() {
        try {
            transaction.commit()
            sess.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun query(hql: String): org.hibernate.Query<*> {
        return sess.createQuery(hql)
    }

    override fun criteria(arg0: Class<*>): org.hibernate.Criteria {
        return sess.createCriteria(arg0)
    }

    override fun sqlQuery(sql: String): org.hibernate.SQLQuery<*> {
        return sess.createSQLQuery(sql)
    }

    override fun save(obj: Any) {
        sess.save(obj)
    }

    override fun update(obj: Any) {
        sess.update(obj)
    }

    override fun delete(obj: Any) {
        sess.delete(obj)
    }

    override fun <T> get(entityClazz: Class<*>, id: Serializable): T {
        return sess.get(entityClazz, id) as T
    }

    override fun findCount(entityClazz: Class<*>): Long {
        val l = find("select count(c) from " + entityClazz.simpleName + "as c")
        return if (l != null && l.size == 1) l[0] as Long else 0
    }

    override fun find(hql: String): List<*> {
        return sess.createQuery(hql).list()
    }

    override fun find(hql: String, vararg params: Any): List<*> {
        val query = sess.createQuery(hql)
        var i = 0
        val len = params.size
        while (i < len) {
            query.setParameter(i.toString() + "", params[i])
            i++
        }
        return query.list()
    }

    override fun findByPage(hql: String, pageNo: Int, pageSize: Int): List<*> {
        return sess.createQuery(hql).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list()
    }

    override fun findByPage(hql: String, pageNo: Int, pageSize: Int, vararg params: Any): List<*> {
        val query = sess.createQuery(hql)
        var i = 0
        val len = params.size
        while (i < len) {
            query.setParameter(i.toString() + "", params[i])
            i++
        }
        return query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list()
    }

    companion object {

        private var factory: SessionFactory? = null

        @Synchronized private fun openSession(wrapper: ContextWrapper): Session {
            synchronized(HibernateDao::class.java) {
                if (null == factory) {
                    factory = wrapper.applicationContext.getBean("sessionFactory", SessionFactory::class.java)
                }
                return factory!!.openSession()
            }
        }
    }

}
