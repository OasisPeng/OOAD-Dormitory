package com.OOAD.service;

import com.OOAD.dao.OpenTimeDao;
import com.OOAD.domain.OpenTime;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IOpenTimeService {
    public List<OpenTime> GetAll();
    public int Add(OpenTime openTime);
    public int DeleteById(int id);
    public OpenTime GetById(int id);
    public int Update(OpenTime openTime);
}
