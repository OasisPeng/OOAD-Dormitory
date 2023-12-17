package com.OOAD.service.impl;

import com.OOAD.dao.SysUserDao;
import com.OOAD.dao.UserDao;
import com.OOAD.domain.Dorm;
import com.OOAD.domain.SysUser;
import com.OOAD.domain.User;
import com.OOAD.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
    @Autowired
    UserDao userDao;
    @Autowired
    SysUserDao sysUserDao;
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
            if (user.getPassword() != null) {
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                LambdaQueryWrapper<SysUser> lqw = new LambdaQueryWrapper<>();
                lqw.eq(SysUser::getUsername, user.getId().toString());
                SysUser sysUser = sysUserDao.selectOne(lqw);
                sysUser.setPassword(user.getPassword());
                sysUserDao.updateById(sysUser);
            }
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

    @Override
    public int insertByList(List<User> list) {

        int size = 0;
        for (User user: list) {
            SysUser sysUser = new SysUser();
            sysUser.setRole("user");
            sysUser.setUsername(user.getId().toString());
            sysUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userDao.insert(user);
            sysUserDao.insert(sysUser);
            size++;
        }
        return size;
    }

}
