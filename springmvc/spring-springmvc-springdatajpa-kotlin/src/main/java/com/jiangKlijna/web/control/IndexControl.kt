package com.jiangKlijna.web.control

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class IndexControl : BaseControl() {

    @RequestMapping("/index.do")
    fun index(): String = "index"

}
