package com.OOAD.controller;

import com.OOAD.domain.User;
import com.OOAD.service.impl.UserServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    //分页查询
    @GetMapping("/listPage")
    public Result listPage(
            @RequestParam int pageNum,
            @RequestParam int pageSize,
            @RequestParam String name
    ) {
        Page<User> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !name.equals("null")) {
            lambdaQueryWrapper.like(User::getName, name);// 设置查询条件
        }

        IPage<User> res = studentService.page(page, lambdaQueryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", res.getTotal());
        map.put("records", res.getRecords());

        Result result = new Result();
        result.setData(map);
        result.setCode(Code.UPDATE_OK);
        result.setMsg("查询成功");

        return result;
    }

    @PostMapping("/getMatchingUsers")
    public Result getMatchingUsers(@RequestBody User currentUser) {
        List<Map.Entry<Integer, Double>> matchingUsers = studentService.calculateMatchingUsers(currentUser);
        Result result = new Result();
        if (matchingUsers == null) {
            result.setData("Err");
            result.setCode(Code.UPDATE_ERR);
            result.setMsg("查询失败，请重试");
        } else {
            result.setData(matchingUsers);
            result.setCode(Code.UPDATE_OK);
            result.setMsg("查询成功");
        }
        return result;
    }

}
