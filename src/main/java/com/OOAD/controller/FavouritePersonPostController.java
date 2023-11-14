package com.OOAD.controller;

import com.OOAD.domain.FavouritePersonPost;
import com.OOAD.service.FavouritePersonPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/favouritePersonPost")
public class FavouritePersonPostController {
    @Autowired
    FavouritePersonPostService service;
    @PostMapping
    public Result insert(@RequestBody FavouritePersonPost f) {
        int i = service.add(f);
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
    @DeleteMapping
    public Result delete(@RequestBody FavouritePersonPost f) {
        int i = service.delete(f);
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
    @GetMapping("/post/{id}")
    public Result getByPostId(@PathVariable int id) {
        List<FavouritePersonPost> list = service.getByPostId(id);
        Result result = new Result();
        if (!list.isEmpty()) {
            result.setMsg("查询成功");
            result.setCode(Code.GET_OK);
            result.setData(list);
        } else {
            result.setMsg("未查询到任何信息请重试");
            result.setCode(Code.GET_Err);
            result.setData("Empty");
        }
        return result;
    }
    @GetMapping("/user/{id}")
    public Result getByPersonId(@PathVariable int id) {
        List<FavouritePersonPost> list = service.getByPersonId(id);
        Result result = new Result();
        if (!list.isEmpty()) {
            result.setMsg("查询成功");
            result.setCode(Code.GET_OK);
            result.setData(list);
        } else {
            result.setMsg("未查询到任何信息请重试");
            result.setCode(Code.GET_Err);
            result.setData("Empty");
        }
        return result;
    }
}
