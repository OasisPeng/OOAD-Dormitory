package com.OOAD.service;

import com.OOAD.domain.PersonPost;
import com.OOAD.domain.TeamPost;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TeamPostService {
    public int add(TeamPost p);
    public int deleteById(int id);
    public int update(TeamPost p);
    public List<TeamPost> getAll();
    public TeamPost getById(int id);
    public List<TeamPost> getByTeamId(int teamId);
}
