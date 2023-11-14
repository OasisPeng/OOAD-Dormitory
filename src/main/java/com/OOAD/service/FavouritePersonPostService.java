package com.OOAD.service;

import com.OOAD.domain.FavouritePersonPost;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FavouritePersonPostService {
    public int add(FavouritePersonPost f);
    public int delete(FavouritePersonPost f);

    public List<FavouritePersonPost> getByPostId(int postId);
    public List<FavouritePersonPost> getByPersonId(int personId);
}
