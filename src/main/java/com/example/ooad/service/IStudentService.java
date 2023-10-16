package com.example.ooad.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whl
 * @since 2023-10-16
 */
@Transactional
public interface IStudentService {
    boolean login(int id, String password);
}
