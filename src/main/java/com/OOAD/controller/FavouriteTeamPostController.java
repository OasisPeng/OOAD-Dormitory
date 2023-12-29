package com.OOAD.controller;

import com.OOAD.domain.*;
import com.OOAD.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/favouriteTeamPost")
public class FavouriteTeamPostController {
    @Autowired
    FavouriteTeamPostService service;
    @Autowired
    TeamPostService teamPostService;
    @Autowired
    IUserService userService;
    @PostMapping
    public Result insert(@RequestBody FavouriteTeamPost f) {
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
    public Result delete(@RequestBody FavouriteTeamPost f) {
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
        List<FavouriteTeamPost> list = service.getByPostId(id);
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
        List<FavouriteTeamPost> list = service.getByPersonId(id);
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
    @GetMapping("/like/{userId}")
    public Result getLikesByPostIdOfUser(@PathVariable int userId) {  //个人发帖获赞情况
        List<TeamPost> list = teamPostService.getByWriterId(userId); //个人发的所有帖子
        Result result = new Result();
        if (!list.isEmpty()) {
            List<Map<String, Object>> res = new ArrayList<>();
            for (TeamPost post:list) {  //根据帖子id查找有没有人点赞
                List<FavouriteTeamPost> likelist = service.getByPostId(post.getId());
                for (FavouriteTeamPost favourite:likelist) {
                    User user = userService.getById(favourite.getPersonId());
                    if(user.getId() != userId) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("postId", post.getId());
                        map.put("postTitle", post.getTitle());
                        map.put("likeByName", user.getName());
                        map.put("likeById", user.getId());
                        res.add(map);
                    }
                }
            }
            result.setMsg("查询成功");
            result.setCode(Code.GET_OK);
            result.setData(res);
        } else {
            result.setMsg("未查询到任何信息请重试");
            result.setCode(Code.GET_Err);
            result.setData("Empty");
        }
        return result;
    }
}
