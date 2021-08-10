package com.forsea.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 渲染到前端的用户数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Long uid;
    private String username;
    private String license;
    private String phone;
    private Integer status;
}
