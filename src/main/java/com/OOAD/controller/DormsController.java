package com.OOAD.controller;

import com.OOAD.domain.Dorm;
import com.OOAD.service.impl.DormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/dorms")
public class DormsController {
    @Autowired
    DormServiceImpl dormService;
    @GetMapping()
    public Result getAll(@RequestParam int pageSize, @RequestParam int pageNum) {
        List<Dorm> dorms = dormService.getAll(pageSize, pageNum);
        Result result = new Result();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", dorms.size());
        map.put("records", dorms);
        if (dorms == null || dorms.isEmpty()) {
            result.setData("Err");
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败或查询为空，请重试");
        } else {
            result.setData(map);
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
        }
        return result;
    }
}
