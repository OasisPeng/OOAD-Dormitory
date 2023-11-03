package com.OOAD.controller;


import com.OOAD.domain.Account;
import com.OOAD.domain.User;
import com.OOAD.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@CrossOrigin(origins = "localhost:8080")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    public Result loginCheck(@RequestBody Account account) {
        int id = account.getUsername();
        String password = account.getPassword();
        User user = userService.login(id, password);
        Result result = new Result();
        if (user == null) {
            result.setCode(Code.LOGIN_ERR);
            result.setData("Err");
            result.setMsg("登录失败，账户名或者密码错误");
        } else {
            result.setCode(Code.LOGIN_OK);
            result.setData(user);
            result.setMsg("登录成功");
        }
        return result;
    }

    @GetMapping("/{id}")
    public Result getByID(@PathVariable int id) {
        User user = userService.getById(id);
        Result result = new Result();
        if (user == null) {
            result.setData("Err");
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败，请重试");
        } else {
            result.setData(user);
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
        }
        return result;
    }
    @PutMapping()
    public Result updateAUser(@RequestBody User user) {
        boolean flag = userService.updateById(user);
        Result result = new Result();
        if (flag) {
            result.setCode(Code.UPDATE_OK);
            result.setData("OK");
            result.setMsg("更新成功");
        } else {
            result.setCode(Code.UPDATE_ERR);
            result.setData("Err");
            result.setMsg("更新失败，请重试");
        }
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteByID(@PathVariable int id) {
        boolean flag = userService.deleteById(id);
        Result result = new Result();
        if (flag) {
            result.setCode(Code.DELETE_OK);
            result.setData("OK");
            result.setMsg("删除用户成功");
        } else {
            result.setCode(Code.DELETE_ERR);
            result.setData("Err");
            result.setMsg("删除用户失败，请重试");
        }
        return result;
    }

    @PostMapping()
    public Result Insert(@RequestBody User user) {
        boolean flag = userService.insert(user);
        Result result = new Result();
        if (flag) {
            result.setCode(Code.INSERT_OK);
            result.setData("OK");
            result.setMsg("新增用户成功");
        } else {
            result.setCode(Code.INSERT_ERR);
            result.setData("Err");
            result.setMsg("新增用户失败，请重试");
        }
        return result;
    }
    @GetMapping("/team/{id}")
    public Result getTeamID(@PathVariable int id){
        int teamid = userService.selectTeamID(id);
        Result result = new Result();
        if (teamid == -1) {
            result.setData("Err");
            result.setCode(Code.GET_Err);
            result.setMsg("用户不存在请重试");
        } else if (teamid == -2){
            result.setData("Err");
            result.setCode(Code.GET_Err);
            result.setMsg("用户尚未加入组队");
        } else {
            result.setData(teamid);
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
        }
        return result;
    }
}


