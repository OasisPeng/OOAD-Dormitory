package com.baomidou.domain;

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
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sex;

    private Integer id;

    private Integer team_Id;

    private Integer dorm_Id;

    private Integer bed;

    private String password;

    @Version
    private Integer version;

    private String name;

    private String college;

    private String timetable1;

    private String timetable2;

    private String sleep_Habit;

    private String clean;

    private String nap;

    private String temperature;

    private String is_Quiet;

    private String character;


}
