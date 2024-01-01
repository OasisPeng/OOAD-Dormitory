package com.OOAD.service;

import com.OOAD.domain.Application;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ApplicationService {
    public int deleteAllType1(int personId);
    public int add(Application a);
    public int delete(Application a);
    public List<Application> getByTeamId(int id);
    public List<Application> getByPersonId(int id);
}
