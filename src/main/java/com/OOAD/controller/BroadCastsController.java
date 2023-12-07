package com.OOAD.controller;

import com.OOAD.domain.BroadcastMsg;
import com.OOAD.service.IBroadCastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/broadCasts")
public class BroadCastsController {
    @Autowired
    IBroadCastService service;
    @GetMapping
    public Result getAll() {
        Result result = new Result();
        List<BroadcastMsg> list = service.getAll();
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

