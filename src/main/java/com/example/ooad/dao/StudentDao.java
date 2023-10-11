package com.example.ooad.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ooad.bean.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentDao extends BaseMapper<Student> {
}
