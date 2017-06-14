package com.jiangKlijna.web.dao

import com.jiangKlijna.web.bean.User

interface IUserDao {

    fun save(obj: User)

    fun update(obj: User)

    fun delete(id: Int)

    operator fun get(id: Int): User

}
