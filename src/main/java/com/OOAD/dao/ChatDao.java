package com.OOAD.dao;

import com.OOAD.domain.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since 2023-12-01
 */
@Mapper
public interface ChatDao extends BaseMapper<Chat> {

}
