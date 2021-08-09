package com.forsea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户部分常用属性实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private Long uid;
    private String username;
    private String license;
    private String phone;
}
