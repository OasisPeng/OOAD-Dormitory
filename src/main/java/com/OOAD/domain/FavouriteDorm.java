package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Mapper;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("favouriteDorm")
public class FavouriteDorm {
    Integer personId;
    Integer dormId;
    Integer teamId;
}
