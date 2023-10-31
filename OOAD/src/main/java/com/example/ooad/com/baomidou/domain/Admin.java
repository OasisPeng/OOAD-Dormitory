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
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private String account;

    private String password;

    @Version
    private Integer version;


}
