package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(value = "Chat对象", description = "")
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("聊天内容")
    private String content;

    @ApiModelProperty("发送方")
    private String fromuser;

    @ApiModelProperty("接收方")
    private String touser;

    @ApiModelProperty("发送方头像")
    private String fromavatar;

    @ApiModelProperty("接收方头像")
    private String toavatar;

    @ApiModelProperty("时间")
    private LocalDateTime time;

    @ApiModelProperty("消息类型")
    private String type;

    @ApiModelProperty("是否已读")
    private int readed;

    @ApiModelProperty("是否是群聊")
    @TableField("isGroup")
    private int isGroup;

    @ApiModelProperty("发送方姓名")
    @TableField("fromuserName")
    private String fromuserName;

    @ApiModelProperty("接收方姓名")
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
