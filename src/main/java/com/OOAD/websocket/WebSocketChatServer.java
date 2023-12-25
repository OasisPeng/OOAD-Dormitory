package com.OOAD.websocket;


import com.OOAD.domain.Chat;
import com.OOAD.service.IChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.Resource;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chatServer")
@Component
public class WebSocketChatServer implements InitializingBean {
    //记录在线连接数
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    @Resource
    IChatService chatService;
    static IChatService staticChatService;

    //连接建立成功调用的方法
    @OnOpen
    public void onOpen(Session session) {
        sessionMap.put(session.getId(), session);
    }

    //连接关闭调用的方法
    @OnClose
    public void onClose(Session session) {
        sessionMap.remove(session.getId());
    }

    //收到客户端消息后调用的方法  中转站
    @OnMessage
    public void onMessage(String message, Session fromSession) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());


        try {
            // 将 JSON 字符串转换为 Person 对象
            Chat chat = objectMapper.readValue(message, Chat.class);
            chat.setTime(LocalDateTime.now());
            //存储数据到数据库
            staticChatService.save(chat);
            String jsonstr = objectMapper.writeValueAsString(chat);
            this.sendAllMessage(jsonstr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(Session fromSession, String message) {
        sessionMap.values().forEach(session -> {
            if (fromSession != session) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException ignored) {

                }
            }
        });
    }
    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception ignored){

        }
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        staticChatService = chatService;
    }
}
