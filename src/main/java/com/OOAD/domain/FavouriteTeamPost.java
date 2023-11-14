package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("favouriteTeamPost")
public class FavouriteTeamPost {
    Integer postId;
    Integer personId;
}
