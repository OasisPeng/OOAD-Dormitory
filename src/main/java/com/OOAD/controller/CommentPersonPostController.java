package com.OOAD.controller;

import com.OOAD.domain.CommentPersonPost;
import com.OOAD.service.ICommentPersonPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentPersonPost")
public class CommentPersonPostController {
    @Autowired
    ICommentPersonPostService service;

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
}
