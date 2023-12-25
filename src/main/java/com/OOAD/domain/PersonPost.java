package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("personPost")
public class PersonPost {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    Integer personId;
    Integer writerId;
    String title;
    String content;
    @Version
    Integer version;
    Timestamp time;
}
