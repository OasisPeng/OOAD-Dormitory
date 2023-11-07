package com.OOAD.domain;

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
 * @author hailong
 * @since 2023-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //队长的学号
    private Integer headId;
    @Version
    private Integer version;
    //容量
    private Integer capacity;
    //选择房间号
    private Integer dorm;
    //当前人数
    private Integer current;
}
