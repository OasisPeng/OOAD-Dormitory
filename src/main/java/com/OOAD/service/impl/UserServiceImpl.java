package com.OOAD.service.impl;

import com.OOAD.dao.SysUserDao;
import com.OOAD.dao.UserDao;
import com.OOAD.domain.SysUser;
import com.OOAD.domain.User;
import com.OOAD.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

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
//    @Cacheable("user")
    @Override
    public List<User> getAll() {
        return userDao.selectList(null);
    }

    @CacheEvict(value = "user", allEntries = true)
    @Override
    public boolean updateById(User user) {
        User originUser = userDao.selectById(user.getId());
        if (originUser == null) {
            return false;
        } else {
            if (user.getPassword() != null && !Objects.equals(user.getPassword(), originUser.getPassword())) {
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
    public List<User> getBySid(int sid) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getSid, sid);
        return  userDao.selectList(lqw);
    }
    @CacheEvict(value = "user", allEntries = true)
    @Override
    public boolean deleteById(int id) {
        int i = userDao.deleteById(id);
        int y = sysUserDao.deleteById(id);
        if (i + y != 2) {
            return false;
        } else {
            return true;
        }
    }
    @CacheEvict(value = "user", allEntries = true)
    @Override
    public boolean insert(User user) {
        User user1 = userDao.selectById(user.getId());
        if (user1 != null) {
            return false;
        }
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        SysUser sysUser = new SysUser();
        sysUser.setRole("user");
        sysUser.setUsername(user.getSid().toString());
        sysUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setPassword(sysUser.getPassword());
        int i = userDao.insert(user);
        int y = sysUserDao.insert(sysUser);
        if (i + y == 2) {
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
    @CacheEvict(value = "user", allEntries = true)
    @Override
    public int insertByList(List<User> list) {
        int size = 0;
        for (User user: list) {
            SysUser sysUser = new SysUser();
            sysUser.setRole("user");
            sysUser.setUsername(user.getSid().toString());
            sysUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setPassword(sysUser.getPassword());
            int id = user.getId();
            User user1 = userDao.selectById(id);
            if (user1 != null) {
                userDao.deleteById(id);
            }
            SysUser user2 = sysUserDao.selectById(id);
            if (user2 != null) {
                sysUserDao.deleteById(id);
            }
            userDao.insert(user);
            sysUserDao.insert(sysUser);
            size++;
        }
        return size;
    }

    @Override
    public List<User> getByTeamId(int teamId) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getTeamId, teamId);
        return userDao.selectList(lqw);
    }

    @Override
    public List<Map.Entry<Integer, Double>> calculateMatchingUsers(User currentUser) {
        List<User> allUsers = getAll();

        // 考虑的属性数组
        String[] consideredProperties = {
                "timetable1",
                "timetable2",
                "smoke",
                "sleepHabit",
                "clean",
                "nap",
                "temperature",
                "isQuiet",
                "characters"
        };

        List<Map.Entry<Integer, Double>> sortedMatchingUsers = allUsers.stream()
                .filter(user -> !user.equals(currentUser)) // 排除当前用户
                .collect(Collectors.toMap(
                        User::getId,
                        user -> {
                            long matchingAttributes = Arrays.stream(consideredProperties)
                                    .filter(propertyName -> {
                                        try {
                                            Field field = User.class.getDeclaredField(propertyName);
                                            field.setAccessible(true);
                                            Object currentValue = field.get(currentUser);
                                            Object otherValue = field.get(user);
                                            return currentValue != null && currentValue.equals(otherValue);
                                        } catch (NoSuchFieldException | IllegalAccessException e) {
                                            return false;
                                        }
                                    })
                                    .count();
                            return (double) matchingAttributes / consideredProperties.length * 100;
                        }
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        return sortedMatchingUsers;
    }

}
