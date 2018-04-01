package com.jiangKlijna.web.control

import com.jiangKlijna.web.app.ContextWrapper
import com.jiangKlijna.web.bean.Result
import org.springframework.web.bind.annotation.ModelAttribute

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession
import java.io.IOException

open class BaseControl : ContextWrapper() {

    protected var request: HttpServletRequest? = null
    protected var response: HttpServletResponse? = null
    protected var session: HttpSession? = null

    @ModelAttribute
    fun setReqAndRes(request: HttpServletRequest, response: HttpServletResponse) {
        this.request = request
        this.response = response
        this.session = request.session
    }

    protected fun response(contentType: String, content: String) {
        try {
            response!!.contentType = contentType
            val w = response!!.writer
            w.print(content)
            w.flush()
            w.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    companion object {

        @JvmStatic protected val errorParameterResult = Result(1, "invalid parameter", "")

        @JvmStatic protected fun testParameter(vararg strs: String?): Boolean {
            for (s in strs) if (s == null || s.isEmpty()) return false
            return true
        }
    }
}
