package com.OOAD.service.impl;

import com.OOAD.dao.AdminDao;
import com.OOAD.domain.Admin;
import com.OOAD.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@Service
public class AdminServiceImpl implements IAdminService{

    @Autowired
    AdminDao adminDao;

    @Override
    public Admin login(int id, String password) {
        Admin admin = adminDao.selectById(id);
        if (admin == null) {
            return null;
        } else {
            if (admin.getPassword().equals(password)) {
                return admin;
            } else {
                return null;
            }
        }
    }
}
