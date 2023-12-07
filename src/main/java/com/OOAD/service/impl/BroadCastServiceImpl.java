package com.OOAD.service.impl;

import com.OOAD.dao.BroadCastDao;
import com.OOAD.domain.BroadcastMsg;
import com.OOAD.service.IBroadCastService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadCastServiceImpl implements IBroadCastService {
    @Autowired
    BroadCastDao dao;
    @Override
    public int add(BroadcastMsg broadcastMsg) {
        return dao.insert(broadcastMsg);
    }

    @Override
    public int delete(int id) {
        return dao.deleteById(id);
    }

    @Override
    public int update(BroadcastMsg broadcastMsg) {
        return dao.updateById(broadcastMsg);
    }

    @Override
    public List<BroadcastMsg> getAll() {
        return dao.selectList(null);
    }

    @Override
    public BroadcastMsg getById(int id) {
        return dao.selectById(id);
    }

    @Override
    public List<BroadcastMsg> getByAdminId(int id) {
        LambdaQueryWrapper<BroadcastMsg> lqw = new LambdaQueryWrapper<>();
        lqw.eq(BroadcastMsg::getAdminId, id);
        return dao.selectList(lqw);
    }
}
