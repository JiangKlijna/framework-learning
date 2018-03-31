package com.jiangklijna.vertx

import io.vertx.core.impl.VertxFactoryImpl
import io.vertx.core.json.JsonObject
import io.vertx.ext.jdbc.JDBCClient
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.CookieHandler
import io.vertx.ext.web.handler.SessionHandler
import io.vertx.ext.web.sstore.LocalSessionStore
import io.vertx.ext.web.templ.ThymeleafTemplateEngine

val vertx = VertxFactoryImpl().vertx()

val engine = ThymeleafTemplateEngine.create()

val client = JDBCClient.createShared(vertx, JsonObject()
        .put("url", "jdbc:postgresql://192.168.200.65:5432/test")
        .put("driver_class", "org.postgresql.Driver")
        .put("max_pool_size", 30)
        .put("user", "postgres")
        .put("password", "jiangKlijna"))

fun main(args: Array<String>) {
    val router = Router.router(vertx)
    router.route().handler(BodyHandler.create())
    router.route().handler(CookieHandler.create());
    router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

    router.get("/").handler(index)
    router.get("/user/get.json").handler(getUser)

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
}
