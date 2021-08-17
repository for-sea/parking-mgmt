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
@ApiModel("管理员更新用户信息DTO")
public class AdminUpdateDTO {
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id", example = "1")
    private Long uid;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("新密码")
    private String newPassword;
    @ApiModelProperty("确认新密码")
    private String reNewPassword;
    @ApiModelProperty("车牌号")
    private String license;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("用户角色")
    private String role;
    @ApiModelProperty("用户权限")
    private String perms;
    @ApiModelProperty("用户状态")
    private Integer status;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("更新时间")
    private String updateTime;
}
