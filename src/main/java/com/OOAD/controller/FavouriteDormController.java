package com.OOAD.controller;

import com.OOAD.domain.FavouriteDorm;
import com.OOAD.service.IFavouriteDormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favouriteDorm")
public class FavouriteDormController {
    @Autowired
    IFavouriteDormService service;
    @GetMapping("/user/{id}")
    public Result GetByPersonId(@PathVariable int id){
        Result result = new Result();
        List<FavouriteDorm> favouriteDorms = service.getByPerson(id);
        if (favouriteDorms == null || favouriteDorms.isEmpty()) {
            result.setCode(Code.GET_Err);
            result.setData("Err");
            result.setMsg("查询失败，结果可能为空");
        } else {
            result.setCode(Code.GET_OK);
            result.setData(favouriteDorms);
            result.setMsg("查询成功");
        }
        return result;
    }
    @GetMapping("/dorm/{id}")
    public Result GetByDormId(@PathVariable int id){
        Result result = new Result();
        List<FavouriteDorm> favouriteDorms = service.getByDorm(id);
        if (favouriteDorms == null || favouriteDorms.isEmpty()) {
            result.setCode(Code.GET_Err);
            result.setData("Err");
            result.setMsg("查询失败，结果可能为空");
        } else {
            result.setCode(Code.GET_OK);
            result.setData(favouriteDorms);
            result.setMsg("查询成功");
        }
        return result;
    }
    @GetMapping("/team/{id}")
    public Result GetByTeamId(@PathVariable int id){
        Result result = new Result();
        List<FavouriteDorm> favouriteDorms = service.getByTeamId(id);
        if (favouriteDorms == null || favouriteDorms.isEmpty()) {
            result.setCode(Code.GET_Err);
            result.setData("Err");
            result.setMsg("查询失败，结果可能为空");
        } else {
            result.setCode(Code.GET_OK);
            result.setData(favouriteDorms);
            result.setMsg("查询成功");
        }
        return result;
    }
    @PostMapping
    public Result insert(@RequestBody FavouriteDorm favouriteDorm) {
        Result result = new Result();
        int re = service.add(favouriteDorm);
        if (re == -1) {
            result.setCode(Code.INSERT_ERR);
            result.setData("Err");
            result.setMsg("收藏失败，用户尚未加入组队");
        } else if (re == -2) {
            result.setCode(Code.INSERT_ERR);
            result.setData("Err");
            result.setMsg("收藏失败，组队收藏已达上限");
        } else if (re == 1) {
            result.setCode(Code.INSERT_OK);
            result.setData("OK");
            result.setMsg("收藏成功");
        } else {
            result.setCode(Code.INSERT_ERR);
            result.setData("Err");
            result.setMsg("收藏失败，系统错误");
        }
        return result;
    }
    @DeleteMapping
    public Result Delete(@RequestBody FavouriteDorm favouriteDorm) {
        Result result = new Result();
        int re = service.delete(favouriteDorm);
        if (re == 1) {
            result.setCode(Code.DELETE_OK);
            result.setData("OK");
            result.setMsg("取消收藏成功");
        } else {
            result.setCode(Code.DELETE_ERR);
            result.setData("ERR");
            result.setMsg("取消收藏失败, 请重试");
        }
        return result;
    }
}
