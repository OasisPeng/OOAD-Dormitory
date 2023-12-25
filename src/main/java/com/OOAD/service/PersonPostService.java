package com.OOAD.service;

import com.OOAD.domain.PersonPost;
import com.OOAD.domain.TeamPost;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PersonPostService {
    public List<PersonPost> getByTitle(String title);
    public int add(PersonPost p);
    public int deleteById(int id);
    public int update(PersonPost p);
    public List<PersonPost> getAll();
    public PersonPost getById(int id);
    public List<PersonPost> getByPersonId(int personId);
    public List<PersonPost> getByWriterId(int id);

}
