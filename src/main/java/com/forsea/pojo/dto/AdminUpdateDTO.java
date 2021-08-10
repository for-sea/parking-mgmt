package com.forsea.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * 管理员更新用户时的传递参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUpdateDTO {
    @NotNull(message = "用户id不能为空")
    private Long uid;

    private String username;
    private String password;
    private String license;
    @Pattern(regexp = "^(1[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{9}$",
            message = "手机号码格式错误")
    private String phone;

    private String role;
    private String perms;
    private Integer status;

    private Timestamp createTime;
    private Timestamp updateTime;
}
