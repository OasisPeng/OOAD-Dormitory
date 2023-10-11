package com.example.ooad.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ooad.bean.Team;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamDao extends BaseMapper<Team> {

}
