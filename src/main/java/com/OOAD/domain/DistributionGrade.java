package com.OOAD.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("distribution_grade")
public class DistributionGrade {
    String distribution;
    Integer grade;
}
