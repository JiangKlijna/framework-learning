package com.jiangklijna.vertx

import io.vertx.core.AsyncResult
import io.vertx.core.impl.VertxFactoryImpl
import io.vertx.core.json.JsonObject
import io.vertx.ext.jdbc.JDBCClient
import org.junit.Test


class JdbcTest {

    private val tableName = "test${System.currentTimeMillis()}"

    private val vertx = VertxFactoryImpl().vertx()

    val client = JDBCClient.createShared(vertx, JsonObject()
            .put("url", "jdbc:postgresql://192.168.200.65:5432/test")
            .put("driver_class", "org.postgresql.Driver")
            .put("max_pool_size", 30)
            .put("user", "postgres")
            .put("password", "jiangKlijna"))

    @Test
    fun run() {
        client.getConnection {
            if (it.failed()) {
                System.err.println(it.cause().message)
                return@getConnection
            }

            val conn = it.result()
            conn.execute("create table $tableName(id int primary key, name varchar(255))") { res ->
                check(res)
                conn.execute("insert into $tableName values(1,'hello'),(2,'world'),(3,'!')") { insert ->
                    check(insert)
                    conn.update("update $tableName set name='update' where id=3", { update ->
                        check(update)
                        conn.execute("delete from $tableName where id=2", { delete ->
                            check(delete)
                            conn.query("select * from $tableName") { rs ->
                                check(rs)
                                for (line in rs.result().results) {
                                    println(line.encode())
                                }
                                conn.close(check)
                            }
                        })
                    })
                }
            }
        }
        Thread.sleep(1000)
    }

    val check = { it: AsyncResult<out Any> ->
        if (it.failed()) throw RuntimeException(it.cause()) else Unit
    }

    fun check(it: AsyncResult<out Any>) =
            if (it.failed()) throw RuntimeException(it.cause()) else Unit
}
