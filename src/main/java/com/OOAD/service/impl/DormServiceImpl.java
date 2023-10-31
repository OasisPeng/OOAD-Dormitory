package com.OOAD.service.impl;

import com.OOAD.dao.DormDao;
import com.OOAD.domain.Dorm;
import com.OOAD.service.IDormService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@Service
public class DormServiceImpl implements IDormService {

    @Autowired
    DormDao dormDao;
    @Override
    public boolean insert(Dorm dorm) {
        Dorm ori = dormDao.selectById(dorm.getId());
        if (ori != null) {
            return false;
        } else {
            int i = dormDao.insert(dorm);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean deleteByID(int id) {
       int i = dormDao.deleteById(id);
       if (i == 1) {
           return true;
       } else {
           return false;
       }
    }

    @Override
    public Dorm selectByID(int id) {
        Dorm dorm = dormDao.selectById(id);
        if (dorm == null) {
            return null;
        } else {
            return dorm;
        }
    }

    @Override
    public boolean updateByID(Dorm dorm) {
        Dorm dorm1 = dormDao.selectById(dorm.getId());
        if (dorm1 == null) {
            return false;
        } else {
            int i = dormDao.updateById(dorm);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public List<Dorm> getAll() {
        return dormDao.selectList(null);
    }

    @Override
    public List<Dorm> selectByDis(int pageSize, int pageNumber, String dis) {
        LambdaQueryWrapper<Dorm> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Dorm::getDistribution, dis);
        Page<Dorm> page = new Page<>(pageNumber, pageSize);
        return dormDao.selectPage(page, lqw).getRecords();
    }

    @Override
    public List<Dorm> selectByBuilding(int pageSize, int pageNumber, String building) {
        LambdaQueryWrapper<Dorm> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Dorm::getBuilding, building);
        Page<Dorm> page = new Page<>(pageNumber, pageSize);
        return dormDao.selectPage(page, lqw).getRecords();
    }

    @Override
    public List<Dorm> selectByRoomNumber(int pageSize, int pageNumber, String building, String roomNumber) {
        LambdaQueryWrapper<Dorm> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Dorm::getBuilding, building).eq(Dorm::getRoom, roomNumber);
        Page<Dorm> page = new Page<>(pageNumber, pageSize);
        return dormDao.selectPage(page, lqw).getRecords();
    }


}
