package com.example.ooad.domain;

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
 * @author whl
 * @since 2023-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PersonalMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate time;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer sendID;

    private Integer receivedID;

    private String msg;

    @Version
    private Integer version;


}
