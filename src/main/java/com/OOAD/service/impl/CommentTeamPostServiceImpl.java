package com.OOAD.service.impl;

import com.OOAD.dao.CommentTeamPostDao;
import com.OOAD.domain.Application;
import com.OOAD.domain.CommentTeamPost;
import com.OOAD.service.ICommentTeamPostService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentTeamPostServiceImpl implements ICommentTeamPostService {
    @Autowired
    CommentTeamPostDao dao;
    @Override
    public List<CommentTeamPost> GetByPostId(int postId) {
        LambdaQueryWrapper<CommentTeamPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CommentTeamPost::getPostId, postId);
        return dao.selectList(lqw);
    }

    @Override
    public List<CommentTeamPost> GetByPersonId(int personId) {
        LambdaQueryWrapper<CommentTeamPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CommentTeamPost::getPersonId, personId);
        return dao.selectList(lqw);
    }

    @Override
    public int add(CommentTeamPost entity) {
        LambdaQueryWrapper<CommentTeamPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CommentTeamPost::getPersonId, entity.getPersonId()).eq(CommentTeamPost::getPostId, entity.getPostId());
        System.out.println("--------------------------------------------------------------");
        System.out.println(entity.getPostId() + " " + entity.getPersonId());
        System.out.println(dao.selectList(lqw));
        if (dao.selectList(lqw).isEmpty()) {
            return dao.insert(entity);
        } else {
            return -1;
        }
    }

    @Override
    public int delete(CommentTeamPost entity) {
        LambdaQueryWrapper<CommentTeamPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CommentTeamPost::getPersonId, entity.getPersonId()).eq(CommentTeamPost::getPostId, entity.getPostId());
        return dao.delete(lqw);
    }
}
