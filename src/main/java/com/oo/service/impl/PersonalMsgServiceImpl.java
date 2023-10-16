package com.oo.service.impl;

import com.oo.domain.PersonalMsg;
import com.oo.dao.PersonalMsgDao;
import com.oo.service.IPersonalMsgService;
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
public class PersonalMsgServiceImpl extends ServiceImpl<PersonalMsgDao, PersonalMsg> implements IPersonalMsgService {

}
