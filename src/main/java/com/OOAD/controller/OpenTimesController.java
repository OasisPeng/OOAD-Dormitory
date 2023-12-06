package com.OOAD.controller;

import com.OOAD.domain.OpenTime;
import com.OOAD.service.IOpenTimeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/openTimes")
public class OpenTimesController {
    @Autowired
    IOpenTimeService service;
    @GetMapping
    public Result getall(){
        Result result = new Result();
        List<OpenTime> list = service.GetAll();
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
