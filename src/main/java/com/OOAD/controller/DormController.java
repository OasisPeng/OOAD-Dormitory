package com.OOAD.controller;


import com.OOAD.domain.Dorm;
import com.OOAD.service.impl.DormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@RestController
@RequestMapping("/dorm")
public class DormController {
    @Autowired
    DormServiceImpl dormService;
    @PostMapping
    public Result insert(@RequestBody Dorm dorm) {
        System.out.println(dorm);
        boolean flag = dormService.insert(dorm);
        Result result = new Result();
        if (flag) {
            result.setCode(Code.INSERT_OK);
            result.setData("OK");
            result.setMsg("添加成功");
        } else {
            result.setCode(Code.INSERT_ERR);
            result.setData("Err");
            result.setMsg("添加失败");
        }
        return result;
    }
    @DeleteMapping("/{id}")
    public Result deleteByID(@PathVariable int id) {
        boolean flag = dormService.deleteByID(id);
        Result result = new Result();
        if (flag) {
            result.setCode(Code.DELETE_OK);
            result.setData("OK");
            result.setMsg("删除成功");
        } else {
            result.setCode(Code.DELETE_ERR);
            result.setData("Err");
            result.setMsg("删除失败");
        }
        return result;
    }
    @GetMapping("/{id}")
    public Result SelectByID(@PathVariable int id) {
        Dorm dorm = dormService.selectByID(id);
        Result result = new Result();
        if (dorm != null) {
            result.setCode(Code.GET_OK);
            result.setData(dorm);
            result.setMsg("查询成功");
        } else {
            result.setCode(Code.GET_Err);
            result.setData("Err");
            result.setMsg("查询失败");
        }
        return result;
    }

    @PutMapping
    public Result update(@RequestBody Dorm dorm) {
        boolean flag = dormService.updateByID(dorm);
        Result result = new Result();
        if (flag) {
            result.setCode(Code.UPDATE_OK);
            result.setData("OK");
            result.setMsg("更新成功");
        } else {
            result.setCode(Code.UPDATE_ERR);
            result.setData("Err");
            result.setMsg("更新失败");
        }
        return result;
    }



}

