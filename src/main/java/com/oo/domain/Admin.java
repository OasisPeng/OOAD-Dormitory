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
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "account", type = IdType.ASSIGN_ID)
    private String account;

    private String password;

    @Version
    private Integer version;


}
