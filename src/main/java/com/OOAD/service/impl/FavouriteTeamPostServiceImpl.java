package com.OOAD.service.impl;

import com.OOAD.dao.FavouritePersonPostDao;
import com.OOAD.dao.FavouriteTeamPostDao;
import com.OOAD.domain.FavouritePersonPost;
import com.OOAD.domain.FavouriteTeamPost;
import com.OOAD.service.FavouritePersonPostService;
import com.OOAD.service.FavouriteTeamPostService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteTeamPostServiceImpl implements FavouriteTeamPostService {
    @Autowired
    FavouriteTeamPostDao favouriteTeamPostDao;
    @Override
    public int add(FavouriteTeamPost f) {
        LambdaQueryWrapper<FavouriteTeamPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FavouriteTeamPost::getPersonId, f.getPersonId()).eq(FavouriteTeamPost::getPostId, f.getPostId());
        List<FavouriteTeamPost> favouriteTeamPost = favouriteTeamPostDao.selectList(lqw);
        if (!favouriteTeamPost.isEmpty()) {
            return 0;
        }
        return favouriteTeamPostDao.insert(f);
    }

    @Override
    public int delete(FavouriteTeamPost f) {
        LambdaQueryWrapper<FavouriteTeamPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FavouriteTeamPost::getPersonId, f.getPersonId()).eq(FavouriteTeamPost::getPostId, f.getPostId());
        return favouriteTeamPostDao.delete(lqw);
    }


    @Override
    public List<FavouriteTeamPost> getByPostId(int postId) {
        LambdaQueryWrapper<FavouriteTeamPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FavouriteTeamPost::getPostId, postId);
        return favouriteTeamPostDao.selectList(lqw);
    }

    @Override
    public List<FavouriteTeamPost> getByPersonId(int personId) {
        LambdaQueryWrapper<FavouriteTeamPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FavouriteTeamPost::getPersonId, personId);
        return favouriteTeamPostDao.selectList(lqw);
    }

}
