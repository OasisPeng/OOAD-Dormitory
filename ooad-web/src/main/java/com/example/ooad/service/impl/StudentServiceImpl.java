package com.example.ooad.service.impl;

import com.example.ooad.dao.StudentDao;
import com.example.ooad.domain.Student;
import com.example.ooad.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whl
 * @since 2023-10-16
 */
@Service
public class StudentServiceImpl implements IStudentService {
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
