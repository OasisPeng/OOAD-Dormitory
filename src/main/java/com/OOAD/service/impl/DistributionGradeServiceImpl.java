package com.OOAD.service.impl;

import com.OOAD.dao.DistributionGradeDao;
import com.OOAD.domain.DistributionGrade;
import com.OOAD.domain.TeamPost;
import com.OOAD.service.DistributionGradeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionGradeServiceImpl implements DistributionGradeService {
    @Autowired
    DistributionGradeDao dao;
    @Override
    public List<DistributionGrade> getByDistribution(String d) {
        LambdaQueryWrapper<DistributionGrade> lqw = new LambdaQueryWrapper<>();
        lqw.eq(DistributionGrade::getDistribution, d);
        return dao.selectList(lqw);
    }

    @Override
    public int add(DistributionGrade d) {
        return dao.insert(d);
    }
}
