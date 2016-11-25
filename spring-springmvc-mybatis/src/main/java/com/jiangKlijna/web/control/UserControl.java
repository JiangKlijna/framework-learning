package com.jiangKlijna.web.control;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiangKlijna.web.bean.Result;
import com.jiangKlijna.web.service.UserService;

@RequestMapping("/user")
@Controller
public class UserControl extends BaseControl {

    @Resource(name = "userService")
    public UserService us;

    @RequestMapping("/regist")
    public void regist(String username, String password, String action) {
        Result re = testParameter(username, password, action) ? us.regist(username, password) : errorParameterResult;
        switch (action) {
            case "html":
                html(re.setData(username + password).toString());
                break;
            case "json":
                json(re.setData(username + password));
                break;
            case "xml":
                xml(re.setData(username + password));
                break;
            default:
            case "text":
                text(re.setData(username + password).toString());
                break;
        }
    }

    @RequestMapping("/remove")
    public void remove(int id) {
        Result re = us.remove(id);
        xml(re.setData(id));
    }

    @RequestMapping("/find")
    public void find(int id) {
        xml(us.find(id));
    }
}
