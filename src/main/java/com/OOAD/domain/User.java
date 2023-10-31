package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hailong
 * @since 2023-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sex;

    private Integer id;

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

    private String sleepHabit;

    private String clean;

    private String nap;

    private String temperature;

    private String isQuiet;

    private String characters;


}
