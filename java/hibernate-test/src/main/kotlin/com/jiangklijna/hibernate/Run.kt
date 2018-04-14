package com.jiangklijna.hibernate

import com.jiangklijna.Run
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

@Entity
@Table(name = "orm_user")
class User(@Id var id: Long, var name: String) {
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

class HibernateRun : Run {

    private val sessionFactory: SessionFactory = (fun(): SessionFactory {
        val cfg = Configuration()
        cfg.configure("hibernate.cfg.xml")
        return cfg.buildSessionFactory()
    })()

    override fun start() {
        val id = System.currentTimeMillis()
        val session = sessionFactory.openSession()
        val tx = session.beginTransaction()
        val u1 = User(id, "save1")
        val u2 = User(id + 1, "save2")
        session.save(u1)
        session.save(u2)
        session.delete(u2)
        session.update(u1.apply { name = "update1" })
        val u = session[User::class.java, u1.id]
        println("u1=$u1, u=$u, u==u1:${u == u1}, u===u1:${u === u1}")
        tx.commit()
        session.close()
    }
}
