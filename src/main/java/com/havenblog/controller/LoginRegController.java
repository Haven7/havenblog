package com.havenblog.controller;

import com.havenblog.model.RespModel;
import com.havenblog.model.User;
import com.havenblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haven.
 */
@RestController
public class LoginRegController {

    @Autowired
    UserService userService;

    @RequestMapping("/login_error")
    public RespModel loginError() {
        return new RespModel("error", "登录失败!");
    }

    @RequestMapping("/login_success")
    public RespModel loginSuccess() {
        return new RespModel("success", "登录成功!");
    }

    /**
     * 如果自动跳转到这个页面，说明用户未登录，返回相应的提示即可
     * <p>
     * 如果要支持表单登录，可以在这个方法中判断请求的类型，进而决定返回JSON还是HTML页面
     *
     * @return
     */
    @RequestMapping("/login_page")
    public RespModel loginPage() {
        return new RespModel("error", "尚未登录，请登录!");
    }

    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public RespModel reg(User user) {

        int result = userService.reg(user);
        if (result == 0) {
            //成功
            return new RespModel("success", "注册成功!");
        } else if (result == 1) {
            return new RespModel("error", "用户名重复，注册失败!");
        } else {
            //失败
            return new RespModel("error", "注册失败!");
        }
    }
}
