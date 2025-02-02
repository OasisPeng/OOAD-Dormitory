package com.OOAD.dao;

import com.OOAD.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}
