package com.baomidou.service.impl;

import com.baomidou.domain.Student;
import com.baomidou.dao.StudentDao;
import com.baomidou.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements IStudentService {

}
