package com.jiangKlijna.web.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexControl extends BaseControl {

    @RequestMapping("/index.do")
    public String remove() {
        return "index";
    }

}
