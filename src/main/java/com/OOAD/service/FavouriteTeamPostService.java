package com.OOAD.service;

import com.OOAD.domain.FavouritePersonPost;
import com.OOAD.domain.FavouriteTeamPost;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FavouriteTeamPostService {
    public int add(FavouriteTeamPost f);
    public int delete(FavouriteTeamPost f);

    public List<FavouriteTeamPost> getByPostId(int postId);
    public List<FavouriteTeamPost> getByPersonId(int personId);
}
