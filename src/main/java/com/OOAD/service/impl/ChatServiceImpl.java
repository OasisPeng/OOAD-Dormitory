package com.OOAD.service.impl;
import com.OOAD.domain.Chat;
import com.OOAD.service.IChatService;
import com.OOAD.dao.ChatDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author
 * @since 2023-12-01
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatDao, Chat> implements IChatService {

}
