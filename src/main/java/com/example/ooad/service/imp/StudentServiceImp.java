package com.example.ooad.service.imp;

import com.example.ooad.bean.Student;
import com.example.ooad.controller.Code;
import com.example.ooad.dao.StudentDao;
import com.example.ooad.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    StudentDao studentDao;

    @Override
    public boolean login(int id, String password) {
       Student student = studentDao.selectById(id);
       if (student == null) {
           return false;
       } else {
           return password.equals(student.getPassword());
       }

    }
}
