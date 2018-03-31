package com.jiangklijna.vertx

import io.vertx.core.AsyncResult
import io.vertx.ext.web.RoutingContext
import io.vertx.core.json.JsonArray

val check = { it: AsyncResult<out Any> ->
    if (it.failed()) throw RuntimeException(it.cause()) else Unit
}

val index = fun(ctx: RoutingContext) {
    val sess = ctx.session()
    val count: Int = (sess.get("count") ?: 0) + 1
    sess.put("count", count)
    ctx.put("count", count)
    ctx.put("title", "vertx-kotlin")
    engine.render(ctx, "templates/", "index.html", { res ->
        if (res.succeeded()) ctx.response().end(res.result())
        else ctx.fail(res.cause())
    })
}

val getUser: (RoutingContext) -> Unit = { ctx ->
    val userid = ctx.request().getParam("userid").toInt()
    val response = ctx.response()
    client.getConnection {
        check(it)
        val conn = it.result()
        conn.queryWithParams("select * from public.user where id=?", JsonArray().add(userid), { select ->
            check(select)
            response.putHeader("content-type", "application/json").end(select.result().toJson().encodePrettily())
        })
    }
}
