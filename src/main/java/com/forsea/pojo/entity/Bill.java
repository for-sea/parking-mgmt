package com.forsea.pojo.entity;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 账单实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    private Long bid;
    @NotNull(message = "用户id不能为空")
    private Long userId;
    @NotBlank(message = "车牌号不能为空")
    private String license;

    private String beginTime;
    private String endTime;

    private Integer stoped;
    private Integer cost;

    private Timestamp createTime;
    private Timestamp updateTime;
}
