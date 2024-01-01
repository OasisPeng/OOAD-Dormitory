package com.OOAD.controller;

import com.OOAD.domain.Application;
import com.OOAD.domain.Dorm;
import com.OOAD.domain.Team;
import com.OOAD.domain.User;
import com.OOAD.service.ApplicationService;
import com.OOAD.service.IDormService;
import com.OOAD.service.impl.ApplicationServiceImpl;
import com.OOAD.service.impl.TeamServiceImpl;
import com.OOAD.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    TeamServiceImpl teamService;
    @Autowired
    IDormService dormService;

    @PostMapping
    public Result add(@RequestBody Application a) {
        Result result = new Result();
        int i = applicationService.add(a);
        if (i == 0) {
            result.setCode(Code.INSERT_ERR);
            result.setMsg("申请失败,请检查您的申请信息");
            result.setData("Err");
        } else {
            result.setCode(Code.INSERT_OK);
            result.setMsg("申请成功，等候审批");
            result.setData("OK");
        }
        return result;
    }
    @DeleteMapping
    public Result delete(@RequestBody Application a) {
        Result result = new Result();
        int i = 0;
        if (a.getType() == 1) {
          i = applicationService.deleteAllType1(a.getUserId());
        } else {
          i = applicationService.delete(a);
        }
        if (i == 0) {
            result.setCode(Code.DELETE_ERR);
            result.setMsg("删除失败");
            result.setData("Err");
        } else {
            result.setCode(Code.DELETE_OK);
            result.setMsg("删除成功");
            result.setData("OK");
        }
        return result;
    }
    @GetMapping("/team/{id}")
    public Result getByteamId(@PathVariable int id) {
        Result result = new Result();
        List<Application> list = applicationService.getByTeamId(id);
        if (list == null) {
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败");
            result.setData("Err");
        } else {
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
            result.setData(list);
        }
        return result;
    }
    @GetMapping("/user/{id}")
    public Result getByUserId(@PathVariable int id) {
        Result result = new Result();
        List<Application> list = applicationService.getByPersonId(id);
        if (list == null) {
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败");
            result.setData("Err");
        } else {
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
            result.setData(list.get(0));
        }
        return result;
    }

    @GetMapping("/offer/{id}")
    public Result getOffersByTeamId(@PathVariable int id) {
        Result result = new Result();
        List<Application> list = applicationService.getByTeamId(id);
        if (list == null) {
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败");
            result.setData("Err");
        } else {
            list = list.stream()
                    .filter(application -> application.getType() == 0)
                    .collect(Collectors.toList());
            List<Map<String, Object>> res = new ArrayList<>();
            for(Application app:list){
                Map<String, Object> map =new HashMap<>();
                map.put("userId",app.getUserId());
                map.put("teamId", app.getTeamId());
                User user = userService.getById(app.getUserId());
                map.put("userName", user.getName());
                Team team = teamService.selectByID(app.getTeamId());
                map.put("teamName", team.getName());
                map.put("teamHeadId", team.getHeadId());
                res.add(map);
            }
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
            result.setData(res);
        }
        return result;
    }

    @GetMapping("/invitation/{id}")
    public Result getInvitationsByTeamId(@PathVariable int id) {
        Result result = new Result();
        List<Application> list = applicationService.getByPersonId(id);
        if (list == null) {
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败");
            result.setData("Err");
        } else {
            list = list.stream()
                    .filter(application -> application.getType() == 1)
                    .collect(Collectors.toList());
            List<Map<String, Object>> res = new ArrayList<>();
            for(Application app:list){
                Map<String, Object> map =new HashMap<>();
                map.put("teamId", app.getTeamId());
                System.out.println("------------------------------------");
                Team team = teamService.selectByID(app.getTeamId());
                System.out.println(team.getHeadId());
                User user = userService.getById(team.getHeadId());
                map.put("userId", user.getId()); //队长
                map.put("userName", user.getName()); //队长
                map.put("teamName", team.getName());
                res.add(map);
            }
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
            result.setData(res);
        }
        return result;
    }

    @GetMapping("/change/{id}")
    public Result getChangesByTeamId(@PathVariable int id) { //id是被申请换宿舍的人
        Result result = new Result();
        List<Application> list = applicationService.getByPersonId(id);
        if (list == null) {
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败");
            result.setData("Err");
        } else {
            list = list.stream()
                    .filter(application -> application.getType() == 2)
                    .collect(Collectors.toList());
            List<Map<String, Object>> res = new ArrayList<>();
            for(Application app:list){
                Map<String, Object> map =new HashMap<>();
                map.put("userId", app.getTeamId());
                User user = userService.getById(app.getTeamId());
                Dorm dorm = dormService.selectByID(user.getDormId());
                map.put("userName", user.getName());
                map.put("Dorm", dorm.getDistribution()+"-"+dorm.getBuilding()+"-"+dorm.getRoom());
                res.add(map);
            }
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
            result.setData(res);
        }
        return result;
    }
}
