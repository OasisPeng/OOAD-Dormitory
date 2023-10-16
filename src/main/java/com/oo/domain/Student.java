package com.oo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author whl
 * @since 2023-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String gender;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    private Integer team_Id;

    private Integer dorm_Id;

    private Integer bed;

    private String password;

    @Version
    private Integer version;


}
