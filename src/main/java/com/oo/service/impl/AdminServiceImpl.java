package com.oo.service.impl;

import com.oo.domain.Admin;
import com.oo.dao.AdminDao;
import com.oo.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whl
 * @since 2023-10-16
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements IAdminService {

}
