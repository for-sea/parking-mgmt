package com.forsea.pojo;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 账单实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    private Long bid;
    private Long userId;
    private String license;
    private DateTime beginTime;
    private DateTime endTime;
    private Long stoped;
    private Long cost;
    private Timestamp created;
}
