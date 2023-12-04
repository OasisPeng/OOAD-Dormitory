package com.OOAD.controller;

import com.OOAD.domain.FavouriteDorm;
import com.OOAD.service.IFavouriteDormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/favouriteDorms")
public class FavouriteDormsController {
    @Autowired
    IFavouriteDormService service;
    @GetMapping
    public Result GetAll() {
        Result result = new Result();
        List<FavouriteDorm> favouriteDorms = service.getAll();
        if (favouriteDorms == null || favouriteDorms.isEmpty()) {
            result.setCode(Code.GET_Err);
            result.setData("Err");
            result.setMsg("查询失败，结果可能为空");
        } else {
            result.setCode(Code.GET_OK);
            result.setData(favouriteDorms);
            result.setMsg("查询成功");
        }
        return result;
    }
}
