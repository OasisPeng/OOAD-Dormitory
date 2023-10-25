package com.example.ooad.dao;

import com.example.ooad.domain.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author whl
 * @since 2023-10-16
 */
@Mapper
public interface StudentDao extends BaseMapper<Student> {

}
