package com.OOAD.service.impl;

import com.OOAD.dao.FavouritePersonPostDao;
import com.OOAD.domain.FavouritePersonPost;
import com.OOAD.domain.FavouriteTeamPost;
import com.OOAD.service.FavouritePersonPostService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouritePersonPostServiceImpl implements FavouritePersonPostService {
    @Autowired
    FavouritePersonPostDao favouritePersonPostDao;
    @Override
    public int add(FavouritePersonPost f) {
        LambdaQueryWrapper<FavouritePersonPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FavouritePersonPost::getPersonId, f.getPersonId()).eq(FavouritePersonPost::getPostId, f.getPostId());
        List<FavouritePersonPost> favouriteTeamPost = favouritePersonPostDao.selectList(lqw);
        if (!favouriteTeamPost.isEmpty()) {
            return 0;
        }
        return favouritePersonPostDao.insert(f);
    }

    @Override
    public int delete(FavouritePersonPost f) {
        LambdaQueryWrapper<FavouritePersonPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FavouritePersonPost::getPersonId, f.getPersonId()).eq(FavouritePersonPost::getPostId, f.getPostId());
        return favouritePersonPostDao.delete(lqw);
    }


    @Override
    public List<FavouritePersonPost> getByPostId(int postId) {
        LambdaQueryWrapper<FavouritePersonPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FavouritePersonPost::getPostId, postId);
        return favouritePersonPostDao.selectList(lqw);
    }

    @Override
    public List<FavouritePersonPost> getByPersonId(int personId) {
        LambdaQueryWrapper<FavouritePersonPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FavouritePersonPost::getPersonId, personId);
        return favouritePersonPostDao.selectList(lqw);
    }


}
