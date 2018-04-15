package com.jiangklijna.jooq

import com.jiangklijna.Run
import com.jiangklijna.db.tables.OrmUser.ORM_USER
import com.jiangklijna.db.tables.records.UserRecord
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.util.*

private val ctx: DSLContext = (fun(): DSLContext {
    val pros = Properties().apply {
        val classLoader = Thread.currentThread().contextClassLoader
        val configStream = classLoader.getResourceAsStream("hikari-config.properties")
        load(configStream)
    }
    return DSL.using(HikariDataSource(HikariConfig(pros)), SQLDialect.POSTGRES)
})()


class JooqByGenRun : Run {

    override fun start() {
        val id = System.currentTimeMillis().toInt()
        val u1 = UserRecord(id, "save1")
        val u2 = UserRecord(id + 1, "save2")
        ctx.insertInto(ORM_USER).set(u1).execute()
        ctx.insertInto(ORM_USER).set(u2).execute()
        ctx.delete(ORM_USER).where(ORM_USER.ID.equal(u2.id.toBigDecimal())).execute()
        ctx.update(ORM_USER).set(ORM_USER.NAME, "update1").where(ORM_USER.ID.equal(u1.id.toBigDecimal())).execute()
        val u = ctx.selectFrom(ORM_USER).where(ORM_USER.ID.eq(u1.id.toBigDecimal()))?.fetch()?.single()
        println("u1=$u1, u=$u, u==u1:${u == u1}, u===u1:${u === u1}")
    }
}

class User(var id: Long, var name: String) {
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

class JooqRun : Run {

    override fun start() {
        val ORM_USER = DSL.table("orm_user")
        val ID = DSL.field("id", Long::class.java)
        val NAME = DSL.field("name", String::class.java)
        val id = System.currentTimeMillis()

        val u1 = User(id, "save1")
        val u2 = User(id + 1, "save2")
        ctx.insertInto(ORM_USER).values(u1.id, u1.name).execute()
        ctx.insertInto(ORM_USER).values(u2.id, u2.name).execute()
        ctx.delete(ORM_USER).where(ID.equal(u2.id)).execute()
        ctx.update(ORM_USER).set(NAME, "update1").where(ID.equal(u1.id)).execute()
        val u = ctx.selectFrom(ORM_USER).where(ID.eq(u1.id))?.fetch()?.single()
        println("u1=$u1, u=$u")
    }
}