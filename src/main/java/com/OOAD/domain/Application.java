package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("application")
public class Application {
    Integer teamId;
    Integer userId;
    Integer type;   //0是申请，1是邀请
}
