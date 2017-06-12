package com.jiangKlijna.web.dao

import java.io.Serializable

interface IDao {
    fun close()

    fun query(hql: String): org.hibernate.Query<*>

    fun criteria(arg0: Class<*>): org.hibernate.Criteria

    fun sqlQuery(sql: String): org.hibernate.SQLQuery<*>

    fun save(obj: Any)

    fun update(obj: Any)

    fun delete(obj: Any)

    operator fun <T> get(entityClazz: Class<*>, id: Serializable): T

    fun findCount(entityClazz: Class<*>): Long

    fun find(hql: String): List<*>

    fun find(hql: String, vararg params: Any): List<*>

    fun findByPage(hql: String, pageNo: Int, pageSize: Int): List<*>

    fun findByPage(hql: String, pageNo: Int, pageSize: Int, vararg params: Any): List<*>
}
