package com.baomidou.service.impl;

import com.baomidou.domain.Admin;
import com.baomidou.dao.AdminDao;
import com.baomidou.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements IAdminService {

}
