package com.example.ooad.domain;

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
public class Dorm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String distribution;

    private String building;

    private Integer floor;

    private Integer favourite;

    private Integer application;

    private String detail;

    @Version
    private Integer version;


}
