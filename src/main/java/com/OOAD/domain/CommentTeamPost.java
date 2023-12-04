package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("commentTeamPost")
public class CommentTeamPost {
    String content;
    Integer personId;
    Integer postId;
}
