package com.OOAD.service.impl;

import com.OOAD.dao.TeamDao;
import com.OOAD.dao.UserDao;
import com.OOAD.domain.Team;
import com.OOAD.domain.User;
import com.OOAD.service.ITeamService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@Service
public class TeamServiceImpl implements ITeamService{

    @Autowired
    TeamDao teamDao;
    @Autowired
    UserDao userDao;

    @Override
    public int Update(Team team) {
        return teamDao.updateById(team);
    }

    @Override
    public Team selectByID(int id) {
       return teamDao.selectById(id);
    }

    @Override
    public List<Team> selectAll() {
        return teamDao.selectList(null);
    }

    @Override
    public int addUser(int teamID, int userID) {
        Team team = teamDao.selectById(teamID);
        User user = userDao.selectById(userID);
        //用户或者队伍不存在
        if (user == null || team == null) {
            return -4;
            //用户已经加入组队
        } else if (user.getTeamId() != null) {
            return -3;
            //队伍已经满
        } else if (team.getCurrent() >= team.getCapacity()) {
            return -2;
        } else {
            Team nt = new Team();
            nt.setId(teamID);
            nt.setCurrent(team.getCurrent() + 1);
            user.setTeamId(teamID);
            if (team.getDorm() != null) {
                user.setDormId(team.getDorm());
            }
            int i = teamDao.updateById(nt);
            int j = userDao.updateById(user);
            //加入失败
            if (i != 1 || j != 1) {
                return -1;
            }
        }
    return 0;
    }

    @Override
    public int deleteUser(int teamID, int userID) {
        Team team = teamDao.selectById(teamID);
        User user = userDao.selectById(userID);
        if (user == null || team == null) {
            return -4;
        } else if (user.getTeamId() == null) {
            return -3;
        } else if (user.getTeamId() != teamID) {
          return -5;
        } else if (team.getCurrent() <= 0) {
            return -2;
        } else {
            if (team.getHeadId() == userID) {
                LambdaUpdateWrapper<User> selectWrapper = new LambdaUpdateWrapper<>();
                selectWrapper.eq(User::getTeamId, teamID);
                List<User> users = userDao.selectList(selectWrapper);
                for (int i = 0; i < users.size(); i++) {
                    LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
                    updateWrapper.eq(User::getId, users.get(i).getId());
                    updateWrapper.set(User::getTeamId,null);
                    updateWrapper.set(User::getDormId,null);
                    int x = userDao.update(users.get(i), updateWrapper);
                    int y = teamDao.deleteById(teamID);
                }
            } else {
            Team nt = new Team();
            nt.setId(teamID);
            nt.setCurrent(team.getCurrent() - 1);
            user.setTeamId(null);
            LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(User::getId,userID);
            updateWrapper.set(User::getTeamId,null);
            updateWrapper.set(User::getDormId,null);
            int j = userDao.update(user, updateWrapper);
            int i = teamDao.updateById(nt);
            if (nt.getCurrent() == 0) {
                int k = teamDao.deleteById(teamID);
                if (k != 1) {
                    return -1;
                }
            }
            if (i != 1 || j != 1) {
                return -1;
            }
            }
        return 0;
    }
    }

    @Override
    public int creat(Team team) {
        System.out.println(team);
        User user = userDao.selectById(team.getHeadId());
        if (user.getTeamId() != null){
            return -1;
        } else {
            int i =  teamDao.insert(team);
            user.setTeamId(team.getId());
            int k = userDao.updateById(user);
            if (k != 1 || i != 1) {
                return -1;
            }
            return 1;
        }


    }

    @Override
    public int change(int personId1, int personId2) {
        try {
        User user1 = userDao.selectById(personId1);
        User user2 = userDao.selectById(personId2);
        int teamId1 = user1.getTeamId();
        int teamId2 = user2.getTeamId();
        Team team1 = teamDao.selectById(teamId1);
        Team team2 = teamDao.selectById(teamId2);
        int head1 = team1.getHeadId();
        int head2 = team2.getHeadId();
        if (head1 == personId1 || head2 == personId2) {
            return -1;
        }
        int dormId1 = user1.getDormId();
        int dormId2 = user2.getDormId();
        user1.setTeamId(teamId2);
        user1.setDormId(dormId2);
        user2.setTeamId(teamId1);
        user2.setDormId(dormId1);
        int x = userDao.updateById(user1);
        int y = userDao.updateById(user2);
        return x + y;
        } catch (Exception e) {
            return -2;
        }
    }
}
