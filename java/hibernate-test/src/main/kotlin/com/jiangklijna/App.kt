package com.jiangklijna

import com.jiangklijna.hibernate.HibernateRun

interface Run {
    fun start()
}

fun main(args: Array<String>) {
    HibernateRun().start()
}