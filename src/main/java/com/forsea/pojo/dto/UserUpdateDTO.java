package com.forsea.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户更新个人信息DTO")
public class UserUpdateDTO {
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id", example = "1")
    private Long uid;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("旧密码")
    private String oldPassword;
    @ApiModelProperty("新密码")
    private String newPassword;
    @ApiModelProperty("车牌号")
    private String license;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("更新时间")
    private String updateTime;
}
