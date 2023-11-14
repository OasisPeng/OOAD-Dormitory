package com.OOAD.controller;

import com.OOAD.domain.Application;
import com.OOAD.service.impl.ApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    ApplicationServiceImpl applicationService;
    @PostMapping
    public Result add(@RequestBody Application a) {
        Result result = new Result();
        int i = applicationService.add(a);
        if (i == 0) {
            result.setCode(Code.INSERT_ERR);
            result.setMsg("申请失败,请检查您的申请信息");
            result.setData("Err");
        } else {
            result.setCode(Code.INSERT_OK);
            result.setMsg("申请成功，等候审批");
            result.setData("OK");
        }
        return result;
    }
    @DeleteMapping
    public Result delete(@RequestBody Application a) {
        Result result = new Result();
        int i = applicationService.delete(a);
        if (i == 0) {
            result.setCode(Code.DELETE_ERR);
            result.setMsg("删除失败");
            result.setData("Err");
        } else {
            result.setCode(Code.DELETE_OK);
            result.setMsg("删除成功");
            result.setData("OK");
        }
        return result;
    }
    @GetMapping("/team/{id}")
    public Result getByteamId(@PathVariable int id) {
        Result result = new Result();
        List<Application> list = applicationService.getByTeamId(id);
        if (list == null) {
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败");
            result.setData("Err");
        } else {
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
            result.setData(list);
        }
        return result;
    }
    @GetMapping("/user/{id}")
    public Result getByUserId(@PathVariable int id) {
        Result result = new Result();
        List<Application> list = applicationService.getByPersonId(id);
        if (list == null) {
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败");
            result.setData("Err");
        } else {
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
            result.setData(list.get(0));
        }
        return result;
    }

}
