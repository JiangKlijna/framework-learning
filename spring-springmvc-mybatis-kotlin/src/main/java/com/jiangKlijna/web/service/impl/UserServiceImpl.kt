package com.jiangKlijna.web.service.impl

import com.jiangKlijna.web.dao.IUserDao
import org.springframework.stereotype.Service

import com.jiangKlijna.web.bean.Result
import com.jiangKlijna.web.bean.User
import com.jiangKlijna.web.service.BaseService
import com.jiangKlijna.web.service.UserService

import javax.annotation.Resource

@Service("userService")
class UserServiceImpl : BaseService(), UserService {

    @Resource
    private val userDao: IUserDao? = null

    override fun regist(username: String, password: String): Result {
        try {
            userDao!!.save(User(username, password))
            return sucessResult()
        } catch (e: Exception) {
            return errorResult(e.toString())
        }

    }

    override fun remove(id: Int): Result {
        try {
            userDao!!.delete(id)
            return sucessResult()
        } catch (e: Exception) {
            return errorResult(e.toString())
        }

    }

    override fun find(id: Int): Result {
        try {
            return sucessResult(userDao!!.get(id))
        } catch (e: Exception) {
            return errorResult(e.toString())
        }

    }

}
