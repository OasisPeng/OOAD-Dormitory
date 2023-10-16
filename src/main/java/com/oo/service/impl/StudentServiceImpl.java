package com.oo.service.impl;

import com.oo.domain.Student;
import com.oo.dao.StudentDao;
import com.oo.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements IStudentService {
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
