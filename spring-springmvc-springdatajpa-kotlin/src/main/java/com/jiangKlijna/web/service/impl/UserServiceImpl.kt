package com.jiangKlijna.web.service.impl

import com.jiangKlijna.web.dao.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.jiangKlijna.web.bean.Result
import com.jiangKlijna.web.bean.User
import com.jiangKlijna.web.service.BaseService
import com.jiangKlijna.web.service.UserService

import javax.transaction.Transactional

@Transactional
@Service("userService")
class UserServiceImpl : BaseService(), UserService {

    @Autowired
    private val userDao: UserDao? = null

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
            val u = userDao!!.getOne(id)
            return sucessResult(u.toString())
        } catch (e: Exception) {
            return errorResult(e.toString())
        }

    }

}
