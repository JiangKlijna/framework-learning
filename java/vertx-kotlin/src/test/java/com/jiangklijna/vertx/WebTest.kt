package com.jiangklijna.vertx

import io.vertx.core.impl.VertxFactoryImpl
import io.vertx.ext.web.client.WebClient
import org.junit.Test

class WebTest {

    private val vertx = VertxFactoryImpl().vertx()
    private val server = vertx.createHttpServer()
    private val client = WebClient.create(vertx)

    private fun serverStart() {
        server.requestHandler({ request ->
            val response = request.response()
            response.putHeader("content-type", "text/html")

            response.end("<h1>Hello vertx!</h1><h2>${request.uri()}</h2>")
        })
        server.listen(8080)
        println("listen 8080")
    }

    private fun clientRequest() {
        client[8080, "127.0.0.1", "/"].send { ar ->
            if (ar.succeeded()) {
                val response = ar.result()
                println("Got HTTP response with status " + response.statusCode())
                println(response.body())
            } else {
                ar.cause().printStackTrace()
            }
        }
        client.close()
    }

    @Test
    fun run() {
        serverStart()
        clientRequest()
        Thread.sleep(1000)
    }

}
