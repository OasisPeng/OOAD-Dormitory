package com.OOAD.controller;

import com.OOAD.domain.User;
import com.OOAD.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserServiceImpl studentService;
    @GetMapping
    public Result getAll() {
        List<User> list = studentService.getAll();
        Result result = new Result();
        if (list == null) {
            result.setData("Err");
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败，请重试");
        } else {
            result.setData(list);
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
        }
        return result;
    }
}
