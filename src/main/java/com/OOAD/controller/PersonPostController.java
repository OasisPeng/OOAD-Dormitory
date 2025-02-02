package com.OOAD.controller;

import com.OOAD.domain.PersonPost;
import com.OOAD.domain.TeamPost;
import com.OOAD.service.impl.PersonPostServiceImpl;
import com.OOAD.service.impl.TeamPostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personPost")
public class PersonPostController {
    @Autowired
    PersonPostServiceImpl service;
    @GetMapping("/writer/{id}")
    public Result getByWriterID(@PathVariable int id) {
        List<PersonPost> personPost = service.getByWriterId(id);
        Result result = new Result();
        if (personPost != null) {
            result.setMsg("查询成功");
            result.setData(personPost);
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
        List<PersonPost> list = service.getByTitle(title);
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
    public Result insert(@RequestBody PersonPost t) {
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
    public Result update(@RequestBody PersonPost t) {
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
        PersonPost personPost = service.getById(id);
        Result result = new Result();
        if (personPost != null) {
            result.setMsg("查询成功");
            result.setData(personPost);
            result.setCode(Code.GET_OK);
        } else {
            result.setMsg("查询失败，帖子不存在");
            result.setData("Err");
            result.setCode(Code.GET_Err);
        }
        return result;
    }
    @GetMapping("/user/{id}")
    public Result getByPersonId(@PathVariable int id) {
        List<PersonPost> list = service.getByPersonId(id);
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
