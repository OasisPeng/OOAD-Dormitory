package com.OOAD.service;

import com.OOAD.domain.Admin;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@Transactional
public interface IAdminService {

    public Admin login(int id, String password);
}
