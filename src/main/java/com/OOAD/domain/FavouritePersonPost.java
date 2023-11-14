package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("favouritePersonPost")
public class FavouritePersonPost {
    Integer postId;
    Integer personId;
}
