package com.example.ooad.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StudentService {
    boolean login(int id, String password);

}
