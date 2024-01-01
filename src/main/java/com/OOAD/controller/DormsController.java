package com.OOAD.controller;

import com.OOAD.domain.Dorm;
import com.OOAD.domain.TeamPost;
import com.OOAD.domain.User;
import com.OOAD.service.impl.DormServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/dorms")
public class DormsController {
    @Autowired
    DormServiceImpl dormService;
    @GetMapping("/loadPage")
    public Result loadPage(@RequestParam int pageSize, @RequestParam int pageNum) {
        List<Dorm> dorms = dormService.getAll(pageSize, pageNum);
        Result result = new Result();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", dorms.size());
        map.put("records", dorms);
        if (dorms == null || dorms.isEmpty()) {
            result.setData("Err");
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败或查询为空，请重试");
        } else {
            result.setData(map);
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
        }
        return result;
    }
    @GetMapping("/listPage")
    public Result listPage(
            @RequestParam int pageNum,
            @RequestParam int pageSize
    ) {
        Page<Dorm> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        LambdaQueryWrapper<Dorm> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        IPage<Dorm> res = dormService.page(page, lambdaQueryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", res.getTotal());
        map.put("records", res.getRecords());

        Result result = new Result();
        result.setData(map);
        result.setCode(Code.UPDATE_OK);
        result.setMsg("查询成功");

        return result;
    }
    @GetMapping
    public Result getAll() {
        List<Dorm> list = dormService.getAll();
        Result result = new Result();
        if (list == null || list.isEmpty()) {
            result.setData("Err");
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败或查询为空，请重试");
        } else {
            result.setData(list);
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
        }
        return result;
    }
}
