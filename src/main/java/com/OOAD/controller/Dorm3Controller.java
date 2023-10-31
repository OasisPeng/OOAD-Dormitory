package com.OOAD.controller;

import com.OOAD.domain.Dorm;
import com.OOAD.service.impl.DormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dorm3")
public class Dorm3Controller {
    @Autowired
    DormServiceImpl dormService;
    @GetMapping("/{building}")
    public Result SelectByBu(@RequestParam int pageSize, @RequestParam int pageNum, @PathVariable String building){
        List<Dorm> dorms = dormService.selectByBuilding(pageSize, pageNum, building);
        Result result = new Result();
        if (dorms != null) {
            result.setCode(Code.GET_OK);
            result.setData(dorms);
            result.setMsg("查询成功");
        } else {
            result.setCode(Code.GET_Err);
            result.setData("Err");
            result.setMsg("查询失败");
        }
        return result;
    }
    @GetMapping("/{building}/{roomNumber}")
    public Result SelectByRo(@RequestParam int pageSize, @RequestParam int pageNum, @PathVariable String building, @PathVariable String roomNumber){
        List<Dorm> dorms = dormService.selectByRoomNumber(pageSize, pageNum, building, roomNumber);
        Result result = new Result();
        if (dorms != null) {
            result.setCode(Code.GET_OK);
            result.setData(dorms);
            result.setMsg("查询成功");
        } else {
            result.setCode(Code.GET_Err);
            result.setData("Err");
            result.setMsg("查询失败");
        }
        return result;
    }
}
