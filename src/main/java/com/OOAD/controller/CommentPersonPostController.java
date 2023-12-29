package com.OOAD.controller;

import com.OOAD.domain.CommentPersonPost;
import com.OOAD.domain.FavouritePersonPost;
import com.OOAD.domain.PersonPost;
import com.OOAD.domain.User;
import com.OOAD.service.ICommentPersonPostService;
import com.OOAD.service.IUserService;
import com.OOAD.service.PersonPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commentPersonPost")
public class CommentPersonPostController {
    @Autowired
    ICommentPersonPostService service;
    @Autowired
    PersonPostService personPostService;
    @Autowired
    IUserService userService;

    @GetMapping("/post/{id}")
    public Result GetByPostId(@PathVariable int id) {
        List<CommentPersonPost> list = service.GetByPostId(id);
        Result result = new Result();
        if (list == null || list.isEmpty()) {
            result.setCode(Code.GET_Err);
            result.setData("Err");
            result.setMsg("查询失败，结果可能为空");
        } else {
            result.setCode(Code.GET_OK);
            result.setData(list);
            result.setMsg("查询成功");
        }
        return result;
    }
    @GetMapping("/person/{id}")
    public Result GetByPersonId(@PathVariable int id) {
        List<CommentPersonPost> list = service.GetByPersonId(id);
        Result result = new Result();
        if (list == null || list.isEmpty()) {
            result.setCode(Code.GET_Err);
            result.setData("Err");
            result.setMsg("查询失败，结果可能为空");
        } else {
            result.setCode(Code.GET_OK);
            result.setData(list);
            result.setMsg("查询成功");
        }
        return result;
    }

    @PostMapping
    public Result insert(@RequestBody CommentPersonPost commentPersonPost) {
        Result result = new Result();
        int re = service.add(commentPersonPost);
        if (re == 1) {
            result.setCode(Code.INSERT_OK);
            result.setData("OK");
            result.setMsg("评论成功");
        } else {
            result.setCode(Code.INSERT_ERR);
            result.setData("ERR");
            result.setMsg("评论失败，请重试");
        }
        return result;
    }
    @DeleteMapping
    public Result delete(@RequestBody CommentPersonPost commentPersonPost) {
        Result result = new Result();
        int re = service.delete(commentPersonPost);
        if (re == 1) {
            result.setCode(Code.DELETE_OK);
            result.setData("OK");
            result.setMsg("删除评论成功");
        } else {
            result.setCode(Code.DELETE_ERR);
            result.setData("ERR");
            result.setMsg("删除评论失败，请重试");
        }
        return result;
    }
    @GetMapping("/comment/{userId}")
    public Result getLikesByPostIdOfUser(@PathVariable int userId) {  //个人发帖获赞情况
        List<PersonPost> list = personPostService.getByWriterId(userId); //个人发的所有帖子
        Result result = new Result();
        if (!list.isEmpty()) {
            List<Map<String, Object>> res = new ArrayList<>();
            for (PersonPost post:list) {  //根据帖子id查找有没有人点赞
                List<CommentPersonPost> commentlist = service.GetByPostId(post.getId());
                for (CommentPersonPost comment:commentlist) {
                    User user = userService.getById(comment.getPersonId());
                    if(user.getId() != userId) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("postId", post.getId());
                        map.put("postTitle", post.getTitle());
                        map.put("commentByName", user.getName());
                        map.put("commentById", user.getId());
                        map.put("content", comment.getContent());
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
