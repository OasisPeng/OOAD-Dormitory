package com.OOAD.service;

import com.OOAD.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ISysUserService extends UserDetailsService {
    public SysUser findByName(String id);
}
