package com.OOAD.controller;


import com.OOAD.domain.Team;
import com.OOAD.service.impl.TeamServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamServiceImpl teamService;

    @GetMapping("/getSameSex/{sex}")
    public Result getSameSex(@PathVariable String sex) {
        List<Team> list = teamService.getSameSex(sex);
        Result result = new Result();
        if (list == null || list.isEmpty()) {
            result.setData("Err");
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败或查询为空，请重试");
        } else {
            result.setData(list);
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
        }
        return result;
    }
    @GetMapping("/{id}")
    public Result GetByID(@PathVariable int id) {
        Team team = teamService.selectByID(id);
        Result res = new Result();
        if (team == null) {
            res.setCode(Code.GET_Err);
            res.setData("Err");
            res.setMsg("查询失败，队伍不存在");
        } else {
            res.setMsg("查询成功");
            res.setData(team);
            res.setCode(Code.GET_OK);
        }
        return res;
    }
    @PostMapping
    public Result creatATeam(@RequestBody Team team) {
        int i = teamService.creat(team);

        Result res = new Result();
        if (i == -1) {
            res.setCode(Code.INSERT_ERR);
            res.setData("Err");
            res.setMsg("建立队伍失败，用户可能已经拥有组队");
        } else {
            res.setCode(Code.INSERT_OK);
            res.setData("OK");
            res.setMsg("建立队伍成功");
        }
        return res;
    }
    @PostMapping("/change/{personId1}/{personId2}")
    public Result change(@PathVariable int personId1, @PathVariable int personId2) {
        int x = teamService.change(personId1, personId2);
        Result result = new Result();
        if (x == 2) {
            result.setMsg("换宿舍成功");
            result.setCode(Code.INSERT_OK);
            result.setData("OK");
        } else if (x == -1) {
            result.setMsg("换宿舍失败,队长不允许更换");
            result.setCode(Code.INSERT_ERR);
            result.setData("ERR");
        } else {
            result.setMsg("换宿舍失败");
            result.setCode(Code.INSERT_ERR);
            result.setData("ERR");
        }
        return result;
    }

    @PostMapping("/{teamID}/{UserID}")
    public Result addAUser(@PathVariable int teamID, @PathVariable int UserID) {
        int i = teamService.addUser(teamID, UserID);
        Result result = new Result();
        if (i == 0) {
            result.setCode(Code.INSERT_OK);
            result.setData("OK");
            result.setMsg("添加成功");
        } else {
            result.setCode(Code.INSERT_ERR);
            result.setData("Err");
            if (i == -4) {
                result.setMsg("用户或者队伍不存在");
            } else if (i == -3) {
                result.setMsg("用户已经加入组队");
            } else if (i == -2) {
                result.setMsg("队伍已满");
            } else {
                result.setMsg("加入失败,请重试");
            }
        }
        return result;
    }
    @DeleteMapping("/{teamID}/{UserID}")
    public Result deleteAUser(@PathVariable int teamID, @PathVariable int UserID) {
        int i = teamService.deleteUser(teamID, UserID);
        Result result = new Result();
        if (i == 0) {
            result.setCode(Code.DELETE_OK);
            result.setData("OK");
            result.setMsg("删除成功");
        } else {
            result.setCode(Code.DELETE_ERR);
            result.setData("Err");
            if (i == -4) {
                result.setMsg("用户或者队伍不存在");
            } else if (i == -3) {
                result.setMsg("用户尚未加入组队");
            } else if (i == -2) {
                result.setMsg("队伍已经清空");
            } else if (i == -5){
                result.setMsg("该用户不在该队伍中，请重试");
            } else {
                result.setMsg("删除失败,请重试");
            }
        }
        return result;
    }
    @GetMapping("/getUnFull/{sex}")
    public Result GetUnFull(@PathVariable String sex) {
        List<Team> list = teamService.getAllUnFullTeam(sex);
        Result result = new Result();
        if (list == null || list.isEmpty()) {
            result.setData("Err");
            result.setCode(Code.GET_Err);
            result.setMsg("查询失败或查询为空，请重试");
        } else {
            result.setData(list);
            result.setCode(Code.GET_OK);
            result.setMsg("查询成功");
        }
            return result;
    }
    @GetMapping("/submit")
    public Result submitDormById(@RequestParam int teamId, @RequestParam String dormName) {
        Team team = teamService.selectByID(teamId);
        team.setSubmittedDorm(dormName);
        int i = teamService.Update(team);
        Result res = new Result();
        if (i == -1) {
            res.setCode(Code.UPDATE_ERR);
            res.setData("Err");
            res.setMsg("查询失败");
        } else {
            res.setMsg("查询成功");
            res.setData(team);
            res.setCode(Code.UPDATE_OK);
        }
        return res;
    }
}

