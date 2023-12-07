package com.OOAD.service;

import com.OOAD.domain.BroadcastMsg;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IBroadCastService {
    public int add(BroadcastMsg broadcastMsg);
    public int delete(int id);
    public int update(BroadcastMsg broadcastMsg);
    public List<BroadcastMsg> getAll();
    public BroadcastMsg getById(int id);
    public List<BroadcastMsg> getByAdminId(int id);
}
