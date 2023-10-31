package com.baomidou.service.impl;

import com.baomidou.domain.Team;
import com.baomidou.dao.TeamDao;
import com.baomidou.service.ITeamService;
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
public class TeamServiceImpl extends ServiceImpl<TeamDao, Team> implements ITeamService {

}
