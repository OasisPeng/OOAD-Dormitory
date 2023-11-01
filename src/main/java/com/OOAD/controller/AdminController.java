package com.OOAD.controller;


import com.OOAD.domain.Account;
import com.OOAD.domain.Admin;
import com.OOAD.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminServiceImpl adminService;

    @PostMapping("/login")
    public Result loginCheck(@RequestBody Account account) {
        int id = account.getUsername();
        String password = account.getPassword();
        Admin admin = adminService.login(id, password);
        Result result = new Result();
        if (admin == null) {
            result.setCode(2001);
            result.setData("Err");
            result.setMsg("登录失败，账户名或者密码错误");
        } else {
            result.setCode(2000);
            result.setData(admin);
            result.setMsg("登录成功");
        }
        return result;
    }
}

