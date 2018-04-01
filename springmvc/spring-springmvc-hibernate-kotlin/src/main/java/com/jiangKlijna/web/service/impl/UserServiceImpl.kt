package com.jiangKlijna.web.service.impl

import org.springframework.stereotype.Service

import com.jiangKlijna.web.bean.Result
import com.jiangKlijna.web.bean.User
import com.jiangKlijna.web.dao.IDao
import com.jiangKlijna.web.service.BaseService
import com.jiangKlijna.web.service.UserService

@Service("userService")
class UserServiceImpl : BaseService(), UserService {

    override fun regist(username: String, password: String): Result {
        try {
            val dao = dao()
            dao.save(User(username, password))
            return sucessResult()
        } catch (e: Exception) {
            return errorResult(e.toString())
        }

    }

    override fun remove(id: Int): Result {
        try {
            val dao = dao()
            dao.delete(User(id, "", ""))
            return sucessResult()
        } catch (e: Exception) {
            return errorResult(e.toString())
        }

    }

    override fun find(id: Int): Result {
        try {
            return sucessResult(dao().get<Any>(User::class.java, id))
        } catch (e: Exception) {
            return errorResult(e.toString())
        }

    }

}
