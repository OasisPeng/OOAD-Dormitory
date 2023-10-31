package com.OOAD.service;

import com.OOAD.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@Transactional
public interface IUserService {
    public User login(int id, String password);

    public List<User> getAll();

    public boolean updateById(User user);

    public User getById(int id);

    public boolean deleteById(int id);

    public boolean insert(User user);
}
