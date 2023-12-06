package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("open_time")
public class OpenTime {
    Integer id;
    Timestamp openTime;
    Timestamp closeTime;
}
