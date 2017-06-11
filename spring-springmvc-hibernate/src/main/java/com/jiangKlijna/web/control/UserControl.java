package com.jiangKlijna.web.control;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiangKlijna.web.bean.Result;
import com.jiangKlijna.web.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
public class UserControl extends BaseControl {

    @Resource(name = "userService")
    public UserService us;

    @ResponseBody
    @RequestMapping("/regist.json")
    public Result regist_json(String username, String password) {
        return testParameter(username, password) ? us.regist(username, password) : errorParameterResult;
    }

    @ResponseBody
    @RequestMapping("/regist.xml")
    public Result regist_xml(String username, String password) {
        return testParameter(username, password) ? us.regist(username, password) : errorParameterResult;
    }

    @ResponseBody
    @RequestMapping("/remove.json")
    public Result remove(int id) {
        return us.remove(id);
    }

    @ResponseBody
    @RequestMapping("/find.xml")
    public Result find(int id) {
        return us.find(id);
    }
}
