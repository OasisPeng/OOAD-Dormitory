package com.OOAD.service;

import com.OOAD.domain.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@Transactional
public interface ITeamService {
    public List<Team> getSameSex(String sex);
    public int Update(Team team);
    public Team selectByID(int id);
    public List<Team> selectAll();
    public int addUser(int teamID, int userID);
    public int deleteUser(int teamID, int userID);
    public int creat(Team team);
    public int change(int personId1, int personId2);
    public List<Team> getAllUnFullTeam(String sex);
}
