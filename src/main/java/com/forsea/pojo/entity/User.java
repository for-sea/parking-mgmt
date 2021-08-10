package com.forsea.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long uid;

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "车牌号不能为空")
    private String license;
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^(1[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{9}$",
    message = "手机号码格式错误")
    private String phone;

    private String role;
    private String perms;
    private Integer status;

    private Timestamp createTime;
    private Timestamp updateTime;
}
