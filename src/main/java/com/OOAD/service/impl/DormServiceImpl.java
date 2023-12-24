package com.OOAD.service.impl;

import com.OOAD.dao.DormDao;
import com.OOAD.dao.TeamDao;
import com.OOAD.domain.Dorm;
import com.OOAD.service.IDormService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @Autowired
    TeamDao teamDao;
    @Override
    public boolean insert(Dorm dorm) {
        LambdaQueryWrapper<Dorm> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Dorm::getDistribution, dorm.getDistribution()).eq(Dorm::getBuilding, dorm.getBuilding()).eq(Dorm::getRoom, dorm.getRoom());;
        List<Dorm> dorms = dormDao.selectList(lqw);
        System.out.println(dorms);
        if (!dorms.isEmpty()) {
            return false;
        }
            int i = dormDao.insert(dorm);
        return i == 1;
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
    public List<Dorm> getAll(int pageSize, int pageNumber) {
        Page<Dorm> page = new Page<>(pageNumber, pageSize);
        return dormDao.selectPage(page,null).getRecords();
    }

    @Override
    public List<Dorm> selectByDis(String dis) {
        LambdaQueryWrapper<Dorm> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Dorm::getDistribution, dis);
        return dormDao.selectList(lqw);
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

    @Override
    public List<Dorm> selectByFloor(int pageSize, int pageNumber, int floor) {
        LambdaQueryWrapper<Dorm> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Dorm::getFloor, floor);
        Page<Dorm> page = new Page<>(pageNumber, pageSize);
        return dormDao.selectPage(page, lqw).getRecords();
    }

    @Override
    public int insertByList(List<Dorm> list) {
        int size = 0;
        for (Dorm dorm: list) {
            dormDao.insert(dorm);
            size++;
        }
        return size;
    }

    @Override
    public int qiang(int id) {
        Dorm dorm = dormDao.selectById(id);
        if (Objects.equals(dorm.getAvailable(), "否")) {
            return -1;
        } else {
            dorm.setAvailable("否");
            return dormDao.updateById(dorm);
        }
    }

    @Override
    public List<Dorm> SelectAvailableRo(String dis, String building) {
        LambdaQueryWrapper<Dorm> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Dorm::getAvailable, '是');
        lqw.eq(Dorm::getDistribution, dis);
        lqw.eq(Dorm::getBuilding, building);
        return dormDao.selectList(lqw);
    }


}
