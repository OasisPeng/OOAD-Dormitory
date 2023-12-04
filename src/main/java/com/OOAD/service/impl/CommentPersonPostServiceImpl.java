package com.OOAD.service.impl;

import com.OOAD.dao.CommentPersonPostDao;
import com.OOAD.domain.CommentPersonPost;
import com.OOAD.domain.CommentTeamPost;
import com.OOAD.service.ICommentPersonPostService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentPersonPostServiceImpl implements ICommentPersonPostService {
    @Autowired
    CommentPersonPostDao dao;
    @Override
    public List<CommentPersonPost> GetByPostId(int postId) {
        LambdaQueryWrapper<CommentPersonPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CommentPersonPost::getPostId, postId);
        return dao.selectList(lqw);
    }

    @Override
    public List<CommentPersonPost> GetByPersonId(int personId) {
        LambdaQueryWrapper<CommentPersonPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CommentPersonPost::getPersonId, personId);
        return dao.selectList(lqw);
    }

    @Override
    public int add(CommentPersonPost entity) {
        LambdaQueryWrapper<CommentPersonPost > lqw = new LambdaQueryWrapper<>();
        lqw.eq(CommentPersonPost ::getPersonId, entity.getPersonId()).eq(CommentPersonPost ::getPostId, entity.getPostId());
        if (dao.selectList(lqw).isEmpty()) {
            return dao.insert(entity);
        } else {
            return -1;
        }
    }

    @Override
    public int delete(CommentPersonPost entity) {
        LambdaQueryWrapper<CommentPersonPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CommentPersonPost::getPersonId, entity.getPersonId()).eq(CommentPersonPost::getPostId, entity.getPostId());
        return dao.delete(lqw);
    }
}
