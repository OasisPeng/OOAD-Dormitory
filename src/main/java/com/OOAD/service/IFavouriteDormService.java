package com.OOAD.service;

import com.OOAD.domain.FavouriteDorm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IFavouriteDormService {
    public List<FavouriteDorm> getAll();
    public List<FavouriteDorm> getByDorm(int dormId);
    public List<FavouriteDorm> getByPerson(int personId);
    public List<FavouriteDorm> getByTeamId(int teamId);
    public int add(FavouriteDorm favouriteDorm);
    public int delete(FavouriteDorm favouriteDorm);

}
