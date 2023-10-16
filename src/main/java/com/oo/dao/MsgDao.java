package com.oo.dao;

import com.oo.domain.Msg;
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
