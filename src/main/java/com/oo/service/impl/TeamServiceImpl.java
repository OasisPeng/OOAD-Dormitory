package com.oo.service.impl;

import com.oo.domain.Team;
import com.oo.dao.TeamDao;
import com.oo.service.ITeamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whl
 * @since 2023-10-16
 */
@Service
public class TeamServiceImpl extends ServiceImpl<TeamDao, Team> implements ITeamService {

}
