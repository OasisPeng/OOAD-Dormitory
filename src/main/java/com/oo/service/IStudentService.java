package com.oo.service;

import com.oo.domain.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whl
 * @since 2023-10-16
 */
public interface IStudentService extends IService<Student> {
    boolean login(int id, String password);
}
