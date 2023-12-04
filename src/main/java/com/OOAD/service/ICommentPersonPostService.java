package com.OOAD.service;

import com.OOAD.domain.CommentPersonPost;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ICommentPersonPostService {
    public List<CommentPersonPost> GetByPostId(int postId);
    public List<CommentPersonPost> GetByPersonId(int personId);
    public int add(CommentPersonPost entity);
    public int delete(CommentPersonPost entity);
}
