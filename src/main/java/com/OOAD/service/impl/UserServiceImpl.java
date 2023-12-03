package com.OOAD.service.impl;

import com.OOAD.dao.UserDao;
import com.OOAD.domain.User;
import com.OOAD.service.IUserService;
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
public class UserServiceImpl implements IUserService {
    @Autowired
    UserDao userDao;
    @Override
    public User login(int id, String password) {
        User user = userDao.selectById(id);
        if (user == null) {
            return null;
        } else {
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                return null;
            }
        }
    }

    @Override
    public List<User> getAll() {
        return userDao.selectList(null);
    }

    @Override
    public boolean updateById(User user) {
        User originUser = userDao.selectById(user.getId());
        if (originUser == null) {
            return false;
        } else {
            int i = userDao.updateById(user);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public User getById(int id) {
       return userDao.selectById(id);
    }

    @Override
    public boolean deleteById(int id) {
        int i = userDao.deleteById(id);
        if (i == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean insert(User user) {
        User user1 = userDao.selectById(user.getId());
        if (user1 != null) {
            return false;
        }
        int i = userDao.insert(user);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int selectTeamID(int id) {
        User user = userDao.selectById(id);
        if (user == null) {
            return -1;
        } else if (user.getTeamId() == null) {
            return -2;
        } else {
            return user.getTeamId();
        }
    }

}
