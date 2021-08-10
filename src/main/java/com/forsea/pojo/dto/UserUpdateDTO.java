package com.forsea.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 用户更新个人信息时的传递参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    @NotNull(message = "用户id不能为空")
    private Long uid;
    private String username;
    private String oldPassword;
    private String newPassword;
    private String license;
    private String phone;
}
