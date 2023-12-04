package com.OOAD.service;

import com.OOAD.domain.CommentTeamPost;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ICommentTeamPostService {
    public List<CommentTeamPost> GetByPostId(int postId);
    public List<CommentTeamPost> GetByPersonId(int personId);
    public int add(CommentTeamPost entity);
    public int delete(CommentTeamPost entity);
}
