package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
@TableName("users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String sex;

    private Integer sid;

    private Integer teamId;

    private Integer dormId;

    private Integer bed;

    private String password;

    @Version
    private Integer version;

    private String name;

    private String college;

    private String timetable1;

    private String timetable2;

    private String smoke;

    private String sleepHabit;

    private String clean;

    private String nap;

    private String temperature;

    private String isQuiet;


    private String characters;

    private String avatar;

    private String grade;

    @TableField("studentType")
    private String studentType;
}
