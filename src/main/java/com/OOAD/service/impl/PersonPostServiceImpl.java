package com.OOAD.service.impl;

import com.OOAD.dao.PersonPostDao;
import com.OOAD.domain.FavouriteTeamPost;
import com.OOAD.domain.PersonPost;
import com.OOAD.domain.TeamPost;
import com.OOAD.service.PersonPostService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonPostServiceImpl implements PersonPostService {
    @Autowired
    PersonPostDao dao;

    @Override
    public List<PersonPost> getByTitle(String title) {
        LambdaQueryWrapper<PersonPost> lqw = new LambdaQueryWrapper<>();
        lqw.like(PersonPost::getTitle, title);
        return dao.selectList(lqw);
    }

    @Override
    public int add(PersonPost p) {
        return dao.insert(p);
    }

    @Override
    public int deleteById(int id) {
        return dao.deleteById(id);
    }

    @Override
    public int update(PersonPost p) {
        return dao.updateById(p);
    }

    @Override
    public List<PersonPost> getAll() {
        return dao.selectList(null);
    }

    @Override
    public PersonPost getById(int id) {
        return dao.selectById(id);
    }

    @Override
    public List<PersonPost> getByPersonId(int personId) {
        LambdaQueryWrapper<PersonPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PersonPost::getPersonId, personId);
        return dao.selectList(lqw);
    }
}
