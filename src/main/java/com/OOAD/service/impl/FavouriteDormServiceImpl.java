package com.OOAD.service.impl;

import com.OOAD.dao.DormDao;
import com.OOAD.dao.FavouriteDormDao;
import com.OOAD.dao.TeamDao;
import com.OOAD.dao.UserDao;
import com.OOAD.domain.*;
import com.OOAD.service.IFavouriteDormService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteDormServiceImpl implements IFavouriteDormService {
    @Autowired
    FavouriteDormDao dao;
    @Autowired
    DormDao dormDao;

    @Override
    public List<FavouriteDorm> getAll() {
        return dao.selectList(null);
    }

    @Override
    public List<FavouriteDorm> getByDorm(int dormId) {
        LambdaQueryWrapper<FavouriteDorm> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FavouriteDorm::getDormId, dormId);
        return dao.selectList(lqw);
    }


    @Override
    public List<FavouriteDorm> getByPerson(int personId) {
        LambdaQueryWrapper<FavouriteDorm> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FavouriteDorm::getPersonId, personId);
        return dao.selectList(lqw);
    }

    @Override
    public List<FavouriteDorm> getByTeamId(int teamId) {
        LambdaQueryWrapper<FavouriteDorm> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FavouriteDorm::getTeamId, teamId);
        return dao.selectList(lqw);
    }

    @Override
    public int add(FavouriteDorm favouriteDorm) {
        if (favouriteDorm.getTeamId() == null) {
            return -1;
        }else {
            LambdaQueryWrapper<FavouriteDorm> lqw = new LambdaQueryWrapper<>();
            lqw.eq(FavouriteDorm::getTeamId, favouriteDorm.getTeamId()).eq(FavouriteDorm::getDormId, favouriteDorm.getDormId());
            List<FavouriteDorm> l = dao.selectList(lqw);
        if (!l.isEmpty()) {
            return -3;
        } else {
            List<FavouriteDorm> tempL = getByTeamId(favouriteDorm.getTeamId());
            if (tempL.size() >= 50) {
                return -2;
            } else {
                Integer dormId = favouriteDorm.getDormId();
                Dorm dorm = dormDao.selectById(dormId);
                dorm.setFavourite(dorm.getFavourite() + 1);
                dormDao.updateById(dorm);
                return dao.insert(favouriteDorm);
            }
        }
        }
    }

    @Override
    public int delete(FavouriteDorm favouriteDorm) {
        LambdaQueryWrapper<FavouriteDorm> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FavouriteDorm::getDormId, favouriteDorm.getDormId()).eq(FavouriteDorm::getPersonId, favouriteDorm.getPersonId());
        Integer dormId = favouriteDorm.getDormId();
        Dorm dorm = dormDao.selectById(dormId);
        dorm.setFavourite(dorm.getFavourite() - 1);
        if (dorm.getFavourite() > 0) {
            dormDao.updateById(dorm);
        }
        return dao.delete(lqw);
    }
}
