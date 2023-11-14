package com.OOAD.controller;

import com.OOAD.domain.TeamPost;
import com.OOAD.service.TeamPostService;
import com.OOAD.service.impl.TeamPostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teamPosts")
public class TeamPostsController {
    @Autowired
    TeamPostServiceImpl service;
    @GetMapping
    public Result getAll() {
        List<TeamPost> list = service.getAll();
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
