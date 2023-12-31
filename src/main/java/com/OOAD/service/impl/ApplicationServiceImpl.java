package com.OOAD.service.impl;

import com.OOAD.dao.ApplicationDao;
import com.OOAD.dao.TeamDao;
import com.OOAD.domain.Application;
import com.OOAD.domain.Team;
import com.OOAD.service.ApplicationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    ApplicationDao dao;
    @Autowired
    TeamDao teamDao;
    @Override
    public int add(Application a) {
        LambdaQueryWrapper<Application> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Application::getUserId, a.getUserId());
        List<Application> list = dao.selectList(lqw);
        Team t = teamDao.selectById(a.getTeamId());
        if (t == null) {
            return 0;
        } else if (t.getCurrent() >= t.getCapacity()) {
            return 0;
        } else {
            if (!list.isEmpty()) {
                return 0;
            } else {
                return dao.insert(a);
            }
        }
    }

    @Override
    public int delete(Application a) {
        LambdaQueryWrapper<Application> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Application::getUserId, a.getUserId()).eq(Application::getTeamId, a.getTeamId());
        return dao.delete(lqw);
    }

    @Override
    public List<Application> getByTeamId(int id) {
        LambdaQueryWrapper<Application> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Application::getTeamId, id).eq(Application::getType, 0);
        return dao.selectList(lqw);
    }

    @Override
    public List<Application> getByPersonId(int id) {
        LambdaQueryWrapper<Application> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Application::getUserId, id);
        return dao.selectList(lqw);
    }
}
