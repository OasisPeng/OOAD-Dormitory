package com.OOAD.controller;

import com.OOAD.domain.BroadcastMsg;
import com.OOAD.domain.TeamPost;
import com.OOAD.service.IBroadCastService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/broadCast")
public class BroadCastController {
    @Autowired
    IBroadCastService service;
    @PostMapping
    public Result insert(@RequestBody BroadcastMsg t) {
        int i = service.add(t);
        Result result = new Result();
        if (i == 1) {
            result.setMsg("新增成功");
            result.setCode(Code.INSERT_OK);
            result.setData("OK");
        } else {
            result.setMsg("新增失败，请重试");
            result.setCode(Code.INSERT_ERR);
            result.setData("ERR");
        }
        return result;
    }
    @DeleteMapping("/{id}")
    public Result deleteByID(@PathVariable int id) {
        int i = service.delete(id);
        Result result = new Result();
        if (i == 1) {
            result.setMsg("删除成功");
            result.setCode(Code.DELETE_OK);
            result.setData("OK");
        } else {
            result.setMsg("删除失败，请重试");
            result.setCode(Code.DELETE_ERR);
            result.setData("ERR");
        }
        return result;
    }
    @PutMapping
    public Result update(@RequestBody BroadcastMsg t) {
        int i = service.update(t);
        Result result = new Result();
        if (i == 1) {
            result.setMsg("更新成功");
            result.setCode(Code.UPDATE_OK);
            result.setData("OK");
        } else {
            result.setMsg("更新失败，请重试");
            result.setCode(Code.UPDATE_ERR);
            result.setData("ERR");
        }
        return result;
    }
    @GetMapping("/{id}")
    public Result getByid(@PathVariable int id) {
        BroadcastMsg broadcastMsg = service.getById(id);
        Result result = new Result();
        if (broadcastMsg != null) {
            result.setMsg("查询成功");
            result.setData(broadcastMsg);
            result.setCode(Code.GET_OK);
        } else {
            result.setMsg("查询失败，帖子不存在");
            result.setData("Err");
            result.setCode(Code.GET_Err);
        }
        return result;
    }
    @GetMapping("/admin/{id}")
    public Result getByAdminId(@PathVariable int id){
        List<BroadcastMsg> broadcastMsg = service.getByAdminId(id);
        Result result = new Result();
        if (broadcastMsg != null) {
            result.setMsg("查询成功");
            result.setData(broadcastMsg);
            result.setCode(Code.GET_OK);
        } else {
            result.setMsg("查询失败，帖子不存在");
            result.setData("Err");
            result.setCode(Code.GET_Err);
        }
        return result;
    }
}
