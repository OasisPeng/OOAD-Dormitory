package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.lang.reflect.Type;

@Data
@TableName("sys_user")
public class SysUser {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    String username;
    String password;
    String role;
}
