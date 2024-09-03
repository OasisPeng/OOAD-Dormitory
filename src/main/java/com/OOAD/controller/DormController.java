package com.OOAD.controller;


import com.OOAD.domain.Dorm;
import com.OOAD.domain.Team;
import com.OOAD.domain.User;
import com.OOAD.service.ITeamService;
import com.OOAD.service.IUserService;
import com.OOAD.service.impl.DormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    @Autowired
    ITeamService teamService;
    @Autowired
    IUserService userService;
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
    @GetMapping("/floor/{n}")
    public Result SelectByFloor(@RequestParam int pageSize, @RequestParam int pageNum, @PathVariable int n) {
        List<Dorm> dorms = dormService.selectByFloor(pageSize, pageNum, n);
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

    @GetMapping("/getDormId")
    public Result getId(@RequestParam String dis, @RequestParam String building, @RequestParam String room ) {
        List<Dorm> dorms = dormService.getDormId(dis,building,room);
        Result result = new Result();
        if (dorms.size() != 0) {
            result.setCode(Code.GET_OK);
            result.setData(dorms.get(0).getId());
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

    @PostMapping("/upload")
    public Result update(@RequestBody List<Dorm> dorms) {
        Result result = new Result();
        int re = dormService.insertByList(dorms);
        if (re == dorms.size()) {
            result.setCode(Code.INSERT_OK);
            result.setData("OK");
            result.setMsg("添加成功");
        } else {
            result.setCode(Code.INSERT_ERR);
            result.setData("ERR");
            result.setMsg("添加失败请重试");
        }
        return result;
    }
    @PostMapping("/qiang/{teamId}/{dormId}")
    public Result qiang(@PathVariable int teamId, @PathVariable int dormId) {
        Result result = new Result();
        Team team = teamService.selectByID(teamId);
        int re = dormService.qiang(dormId);
        if (re == 1) {
            team.setDorm(dormId);
            List<User> teamUser = userService.getByTeamId(teamId);
            int cntUser = 0;
            for (User user : teamUser) {
                user.setDormId(dormId);
                if (userService.updateById(user)) {
                    cntUser++;
                }
            }
            int x = teamService.Update(team);
            if (x == 1 && cntUser == teamUser.size()) {
                result.setCode(Code.INSERT_OK);
                result.setMsg("抢宿舍成功");
                result.setData("OK");
            } else {
                result.setCode(Code.INSERT_ERR);
                result.setMsg("抢宿舍失败，请重试");
                result.setData("Err");
            }
        } else {
            result.setCode(Code.INSERT_ERR);
            result.setMsg("抢宿舍失败，请重试");
            result.setData("Err");
        }
        return result;
    }

}

