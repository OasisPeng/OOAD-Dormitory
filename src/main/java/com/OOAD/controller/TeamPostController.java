package com.OOAD.controller;

import com.OOAD.domain.PersonPost;
import com.OOAD.domain.TeamPost;
import com.OOAD.service.TeamPostService;
import com.OOAD.service.impl.TeamPostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teamPost")
public class TeamPostController {
    @Autowired
    TeamPostServiceImpl service;
    @GetMapping("/writer/{id}")
    public Result deleteByWriterID(@PathVariable int id) {
        List<TeamPost> teamPost = service.getByWriterId(id);
        Result result = new Result();
        if (teamPost != null) {
            result.setMsg("查询成功");
            result.setData(teamPost);
            result.setCode(Code.GET_OK);
        } else {
            result.setMsg("查询失败，帖子不存在");
            result.setData("Err");
            result.setCode(Code.GET_Err);
        }
        return result;
    }
    @GetMapping("/title")
    public Result getByTitle(@RequestParam String title) {
        List<TeamPost> list = service.getByTitle(title);
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
    @PostMapping
    public Result insert(@RequestBody TeamPost t) {
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
        int i = service.deleteById(id);
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
    public Result update(@RequestBody TeamPost t) {
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
        TeamPost teamPost = service.getById(id);
        Result result = new Result();
        if (teamPost != null) {
            result.setMsg("查询成功");
            result.setData(teamPost);
            result.setCode(Code.GET_OK);
        } else {
            result.setMsg("查询失败，帖子不存在");
            result.setData("Err");
            result.setCode(Code.GET_Err);
        }
        return result;
    }
    @GetMapping("/team/{id}")
    public Result getByTeamId(@PathVariable int id) {
        List<TeamPost> list = service.getByTeamId(id);
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


