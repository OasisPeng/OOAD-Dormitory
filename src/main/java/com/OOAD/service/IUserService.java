package com.OOAD.service;

import com.OOAD.domain.Dorm;
import com.OOAD.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    List<User> getBySid(int sid);

    public boolean deleteById(int id);

    public boolean insert(User user);
    public int selectTeamID(int id);
    public int insertByList(List<User> list);
    public List<User> getByTeamId(int teamId);
    List<Map.Entry<Integer, Double>> calculateMatchingUsers(User currentUser);

}
