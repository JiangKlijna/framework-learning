package com.jiangklijna

import com.jiangklijna.mybatis.MybatisRun

interface Run {
    fun start()
}

fun main(args: Array<String>) {
    MybatisRun().start()
}