package com.OOAD.service;

import com.OOAD.domain.Dorm;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
public interface IDormService {
    public boolean insert(Dorm dorm);
    public boolean deleteByID(int id);
    public Dorm selectByID(int id);
    public boolean updateByID(Dorm dorm);
    public List<Dorm> getAll(int pageSize, int pageNumber);
    public List<Dorm> selectByDis(int pageSize, int pageNumber, String dis);
    public List<Dorm> selectByBuilding(int pageSize, int pageNumber, String building);
    public List<Dorm> selectByRoomNumber(int pageSize, int pageNumber, String building, String roomNumber);
    public List<Dorm> selectByFloor(int pageSize, int pageNumber, int floor);
    public int insertByList(List<Dorm> list);
}
