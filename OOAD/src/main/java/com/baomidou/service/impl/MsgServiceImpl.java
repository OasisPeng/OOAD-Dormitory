package com.baomidou.service.impl;

import com.baomidou.domain.Msg;
import com.baomidou.dao.MsgDao;
import com.baomidou.service.IMsgService;
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
public class MsgServiceImpl extends ServiceImpl<MsgDao, Msg> implements IMsgService {

}
