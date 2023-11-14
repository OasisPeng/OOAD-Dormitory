package com.OOAD.service;

import com.OOAD.domain.PersonPost;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PersonPostService {
    public int add(PersonPost p);
    public int deleteById(int id);
    public int update(PersonPost p);
    public List<PersonPost> getAll();
    public PersonPost getById(int id);
    public List<PersonPost> getByPersonId(int personId);


}
