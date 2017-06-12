package com.jiangKlijna.web.service

import com.jiangKlijna.web.app.ContextWrapper
import com.jiangKlijna.web.bean.Result
import com.jiangKlijna.web.dao.IDao

open class BaseService : ContextWrapper() {

    protected fun dao(): IDao {
        return applicationContext.getBean("dao", IDao::class.java)
    }

    protected fun sucessResult(): Result {
        return applicationContext.getBean("sucessResult", Result::class.java)
    }

    protected fun sucessResult(data: Any): Result {
        return sucessResult().setData(data)
    }

    protected fun errorResult(message: String): Result {
        return applicationContext.getBean("errorResult", Result::class.java).setMessage(message)
    }
}
