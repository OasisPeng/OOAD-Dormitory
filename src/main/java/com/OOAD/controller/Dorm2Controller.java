package com.OOAD.controller;

import com.OOAD.domain.Dorm;
import com.OOAD.service.impl.DormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dorm2")
public class Dorm2Controller {
    @Autowired
    DormServiceImpl dormService;
    @GetMapping("/{dis}")
    public Result SelectByDis(@RequestParam int pageSize, @RequestParam int pageNum, @PathVariable String dis){
        List<Dorm> dorms = dormService.selectByDis(pageSize, pageNum, dis);
        Result result = new Result();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", dorms.size());
        map.put("records", dorms);
        if (dorms != null) {
            result.setCode(Code.GET_OK);
            result.setData(map);
            result.setMsg("查询成功");
        } else {
            result.setCode(Code.GET_Err);
            result.setData("Err");
            result.setMsg("查询失败");
        }
        return result;
    }
    @GetMapping("/buildings/{dis}")
    public Result SelectBuildingsByDis(@PathVariable String dis){
        List<Dorm> dorms = dormService.selectByDis(dis);
        Result result = new Result();
        if (dorms != null) {
            List<String> buildingList = dorms.stream()
                    .map(Dorm::getBuilding)
                    .distinct()
                    .collect(Collectors.toList());

            result.setCode(Code.GET_OK);
            result.setData(buildingList);
            result.setMsg("查询成功");
        } else {
            result.setCode(Code.GET_Err);
            result.setData("Err");
            result.setMsg("查询失败");
        }
        return result;
    }
    @GetMapping("/{dis}/{building}")
    public Result SelectAvailableRoByDisAndBuliding(@PathVariable String dis, @PathVariable String building){
        List<Dorm> dorms = dormService.SelectAvailableRo(dis, building);
        Result result = new Result();
        if (dorms != null) {
            List<Integer> rooms = dorms.stream()
                    .map(Dorm::getRoom)
                    .distinct()
                    .collect(Collectors.toList());

            result.setCode(Code.GET_OK);
            result.setData(rooms);
            result.setMsg("查询成功");
        } else {
            result.setCode(Code.GET_Err);
            result.setData("Err");
            result.setMsg("查询失败");
        }
        return result;
    }
}
