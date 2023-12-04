package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("commentPersonPost")
public class CommentPersonPost {
    String content;
    Integer personId;
    Integer postId;
}
