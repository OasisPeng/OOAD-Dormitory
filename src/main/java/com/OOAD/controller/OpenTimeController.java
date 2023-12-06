package com.OOAD.controller;

import com.OOAD.domain.OpenTime;
import com.OOAD.service.IOpenTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/openTime")
public class OpenTimeController {
    @Autowired
    IOpenTimeService service;
    @GetMapping("/{id}")
    public Result getByID(@PathVariable int id) {
        Result result = new Result();
        OpenTime openTime = service.GetById(id);
        if (openTime == null) {
            result.setData("Err");
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败或查询为空，请重试");
        } else {
            result.setData(openTime);
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
        }
        return result;
    }
    @DeleteMapping("/id")
    public Result delete(@PathVariable int id) {
        Result result = new Result();
        int re = service.DeleteById(id);
        if (re != 1) {
            result.setData("Err");
            result.setCode(Code.DELETE_ERR);
            result.setMsg("删除失败，请重试");
        } else {
            result.setData("OK");
            result.setCode(Code.DELETE_OK);
            result.setMsg("删除成功");
        }
        return result;
    }
    @PutMapping
    public Result update(@RequestBody OpenTime openTime) {
        Result result = new Result();
        int re = service.Update(openTime);
        if (re != 1) {
            result.setData("Err");
            result.setCode(Code.UPDATE_ERR);
            result.setMsg("更新失败");
        } else {
            result.setData("OK");
            result.setCode(Code.UPDATE_OK);
            result.setMsg("更新成功");
        }
        return result;
    }
    @PostMapping
    public Result insert(@RequestBody OpenTime openTime) {
        Result result = new Result();
        int re = service.Add(openTime);
        if (re != 1) {
            result.setData("Err");
            result.setCode(Code.INSERT_ERR);
            result.setMsg("添加失败");
        } else {
            result.setData("OK");
            result.setCode(Code.INSERT_OK);
            result.setMsg("添加成功");
        }
        return result;
    }
}
