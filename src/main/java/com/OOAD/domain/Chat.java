package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String content;

    private String fromuser;

    private String touser;

    private String fromavatar;

    private String toavatar;

    private LocalDateTime time;

    private String type;

    private int readed;

    @TableField("isGroup")
    private int isGroup;

    @TableField("fromuserName")
    private String fromuserName;

    @TableField("touserName")
    private String touserName;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getFromavatar() {
        return fromavatar;
    }

    public void setFromavatar(String fromavatar) {
        this.fromavatar = fromavatar;
    }

    public String getToavatar() {
        return toavatar;
    }

    public void setToavatar(String toavatar) {
        this.toavatar = toavatar;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getReaded() {
        return readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

    public String getTouserName() {
        return touserName;
    }

    public void setTouserName(String touserName) {
        this.touserName = touserName;
    }

    public int getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(int isGroup) {
        this.isGroup = isGroup;
    }

    public String getFromuserName() {
        return fromuserName;
    }

    public void setFromuserName(String fromuserName) {
        this.fromuserName = fromuserName;
    }

}
