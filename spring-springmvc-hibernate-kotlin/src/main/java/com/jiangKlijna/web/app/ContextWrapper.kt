package com.jiangKlijna.web.app

import javax.servlet.ServletContext

import org.springframework.context.ApplicationContext
import org.springframework.web.context.ContextLoader
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils

open class ContextWrapper {
    val webApplicationContext: WebApplicationContext
        get() = ContextLoader.getCurrentWebApplicationContext()

    val servletContext: ServletContext
        get() = webApplicationContext.servletContext

    val applicationContext: ApplicationContext
        get() = WebApplicationContextUtils.getWebApplicationContext(servletContext)
}
