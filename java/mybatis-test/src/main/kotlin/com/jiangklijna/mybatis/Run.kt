package com.jiangklijna.mybatis

import com.jiangklijna.Run
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import java.math.BigDecimal

class User(var id: Long, var name: String) {
    constructor(id: BigDecimal, name: String) : this(id.longValueExact(), name)

    override fun toString() = "User@${hashCode()}(id=$id, name='$name')"
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as User
        if (id != other.id) return false
        if (name != other.name) return false
        return true
    }
}

interface UserMapper {
    @Select("select * from orm_user where id=#{id}")
    operator fun get(id: Long): User
    @Insert("insert into orm_user(id,name) values(#{id}, #{name})")
    fun save(u: User)
    @Update("update orm_user set name=#{name} where id=#{id}")
    fun update(u: User)
    @Delete("delete from orm_user where id=#{id}")
    fun delete(id: Long)
}

class MybatisRun : Run {

    private val sessionFactory: SqlSessionFactory = (fun(): SqlSessionFactory {
        val configStream = Resources.getResourceAsStream("mybatis-config.xml")
        return SqlSessionFactoryBuilder().build(configStream)
    })()

    override fun start() {
        val session = sessionFactory.openSession(true)
        val um = session.getMapper(UserMapper::class.java)

        val id = System.currentTimeMillis()
        val u1 = User(id, "save1")
        val u2 = User(id + 1, "save2")
        um.save(u1)
        um.save(u2)
        um.delete(u2.id)
        um.update(u1.apply { name = "update1" })
        val u = um[u1.id]
        println("u1=$u1, u=$u, u==u1:${u == u1}, u===u1:${u === u1}")
    }
}