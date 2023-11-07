package com.OOAD.controller;

import com.OOAD.domain.Team;
import com.OOAD.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamsController {
    @Autowired
    TeamServiceImpl teamService;
    @GetMapping
    public Result getAll(){
        List<Team> re = teamService.selectAll();
        Result res = new Result();
        if (re == null) {
            res.setData("Err");
            res.setCode(Code.GET_Err);
            res.setMsg("查询失败，请重试");
        } else {
            res.setMsg("查询成功");
            res.setCode(Code.GET_OK);
            res.setData(re);
        }
        return res;
    }



}