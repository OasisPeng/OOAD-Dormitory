package com.OOAD.controller;

import com.OOAD.domain.DistributionGrade;
import com.OOAD.service.impl.DistributionGradeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distributionGrade")
public class DistributionGradeController {
    @Autowired
    DistributionGradeServiceImpl service;

    @GetMapping("/{distribution}")
    public Result Get(@PathVariable String distribution){
        Result result = new Result();
        List<DistributionGrade> re = service.getByDistribution(distribution);
        if ( re == null || re.isEmpty()) {
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败，查询列表可能为空");
            result.setData("ERR");
        } else {
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
            result.setData(re);
        }
        return result;
    }

    @PostMapping
    public Result add(@RequestBody DistributionGrade d){
        Result result = new Result();
        int i = service.add(d);
        if (i == 0) {
            result.setCode(Code.INSERT_ERR);
            result.setMsg("添加失败");
            result.setData("ERR");
        } else {
            result.setCode(Code.INSERT_OK);
            result.setMsg("添加成功");
            result.setData("OK");
        }
        return result;
    }
}
