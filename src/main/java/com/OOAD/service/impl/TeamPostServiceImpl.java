package com.OOAD.service.impl;

import com.OOAD.dao.PersonPostDao;
import com.OOAD.dao.TeamPostDao;
import com.OOAD.domain.PersonPost;
import com.OOAD.domain.TeamPost;
import com.OOAD.service.TeamPostService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamPostServiceImpl implements TeamPostService {
    @Autowired
    TeamPostDao dao;
    @Override
    public int add(TeamPost p) {
        return dao.insert(p);
    }

    @Override
    public int deleteById(int id) {
        return dao.deleteById(id);
    }

    @Override
    public int update(TeamPost p) {
        return dao.updateById(p);
    }

    @Override
    public List<TeamPost> getAll() {
        return dao.selectList(null);
    }

    @Override
    public TeamPost getById(int id) {
        return dao.selectById(id);
    }

    @Override
    public List<TeamPost> getByTeamId(int TeamId) {
        LambdaQueryWrapper<TeamPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TeamPost::getTeamId, TeamId);
        return dao.selectList(lqw);
    }
}
