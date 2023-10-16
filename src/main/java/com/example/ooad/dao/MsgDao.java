package com.example.ooad.dao;

import com.example.ooad.domain.Msg;
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
public interface MsgDao extends BaseMapper<Msg> {

}
