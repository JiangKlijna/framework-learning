package com.jiangKlijna.web.control

import javax.annotation.Resource

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

import com.jiangKlijna.web.bean.Result
import com.jiangKlijna.web.service.UserService
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping("/user")
@Controller
class UserControl : BaseControl() {

    @Resource(name = "userService")
    var us: UserService? = null

    @ResponseBody
    @RequestMapping("/regist.json")
    fun regist_json(username: String, password: String): Result {
        return if (BaseControl.testParameter(username, password)) us!!.regist(username, password) else BaseControl.errorParameterResult
    }

    @ResponseBody
    @RequestMapping("/regist.xml")
    fun regist_xml(username: String, password: String): Result {
        return if (BaseControl.testParameter(username, password)) us!!.regist(username, password) else BaseControl.errorParameterResult
    }

    @ResponseBody
    @RequestMapping("/remove.json")
    fun remove(id: Int): Result = us!!.remove(id)

    @ResponseBody
    @RequestMapping("/find.xml")
    fun find(id: Int): Result = us!!.find(id)
}
