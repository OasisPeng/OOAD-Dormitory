package com.OOAD.service;

import com.OOAD.domain.Chat;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2023-12-01
 */
@Transactional
public interface IChatService extends IService<Chat> {

}
