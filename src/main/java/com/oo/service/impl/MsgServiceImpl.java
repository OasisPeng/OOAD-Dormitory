package com.oo.service.impl;

import com.oo.domain.Msg;
import com.oo.dao.MsgDao;
import com.oo.service.IMsgService;
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
public class MsgServiceImpl extends ServiceImpl<MsgDao, Msg> implements IMsgService {

}
