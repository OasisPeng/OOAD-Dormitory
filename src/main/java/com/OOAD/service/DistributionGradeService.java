package com.OOAD.service;

import com.OOAD.domain.DistributionGrade;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DistributionGradeService {
    public List<DistributionGrade> getByDistribution(String d);
    public int add(DistributionGrade d);
}
