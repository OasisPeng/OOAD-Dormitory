package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
 * @author hailong
 * @since 2023-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DormMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private LocalDate time;
    private Integer grades;
    private Integer owner;

    private Integer type;

    private String theme;

    private String msg;

    @Version
    private Integer version;


}
