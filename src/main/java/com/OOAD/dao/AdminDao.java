package com.OOAD.dao;

import com.OOAD.domain.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@Mapper
public interface AdminDao extends BaseMapper<Admin> {

}
