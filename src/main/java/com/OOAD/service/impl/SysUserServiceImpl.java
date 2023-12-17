package com.OOAD.service.impl;

import com.OOAD.dao.SysUserDao;
import com.OOAD.dao.UserDao;
import com.OOAD.domain.SysUser;
import com.OOAD.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    SysUserDao dao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = this.findByName(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名或者密码错误");
        }
        return User
                .withUsername(username)
                .password(sysUser.getPassword())
                .roles(sysUser.getRole())
                .build();
    }


    @Override
    public SysUser findByName(String username) {
        LambdaQueryWrapper<SysUser> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SysUser::getUsername, username);
        List<SysUser> list = dao.selectList(lqw);
        if (list.isEmpty()) {
            return null;
        } else {
        return dao.selectList(lqw).get(0);
        }
    }


}
