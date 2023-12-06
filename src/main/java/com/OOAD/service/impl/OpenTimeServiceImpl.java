package com.OOAD.service.impl;

import com.OOAD.dao.OpenTimeDao;
import com.OOAD.domain.OpenTime;
import com.OOAD.service.IOpenTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenTimeServiceImpl implements IOpenTimeService {
    @Autowired
    OpenTimeDao dao;
    @Override
    public List<OpenTime> GetAll() {
        return dao.selectList(null);
    }

    @Override
    public int Add(OpenTime openTime) {
        return dao.insert(openTime);
    }

    @Override
    public int DeleteById(int id) {
        return dao.deleteById(id);
    }

    @Override
    public OpenTime GetById(int id) {
        return dao.selectById(id);
    }

    @Override
    public int Update(OpenTime openTime) {
        return dao.updateById(openTime);
    }
}
