package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("broadcast_msg")
public class BroadcastMsg {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String content;
    private int adminId;
}
