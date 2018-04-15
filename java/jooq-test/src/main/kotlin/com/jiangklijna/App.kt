package com.jiangklijna

import com.jiangklijna.jooq.JooqByGenRun
import com.jiangklijna.jooq.JooqRun

interface Run {
    fun start()
}

fun main(args: Array<String>) {
    JooqRun().start()
    JooqByGenRun().start()
}