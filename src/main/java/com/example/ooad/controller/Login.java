package com.example.ooad.controller;

import com.example.ooad.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Login {
    @Autowired
    StudentService service;

    @PostMapping("/login")
    @ResponseBody
    public Result LoginCheck(int id, String password, HttpSession session) {
        boolean isOk = service.login(id, password);
        Result result = new Result();
        result.setData(isOk);
        if (isOk) {
            result.setCode(Code.LOGIN_OK);
            result.setMsg("登录成功");
            session.setAttribute("id", id);
            session.setAttribute("password", password);
        } else {
        result.setCode(Code.LOGIN_ERR);
        result.setMsg("登录失败，账户不存在或者密码错误");
        }
        return result;
    }
}
