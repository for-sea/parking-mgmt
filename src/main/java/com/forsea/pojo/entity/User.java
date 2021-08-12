package com.forsea.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户实体类")
public class User {
    @ApiModelProperty(value = "用户id", example = "1")
    private Long uid;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String username;
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("用户密码")
    private String password;
    @NotBlank(message = "车牌号不能为空")
    @ApiModelProperty("车牌号")
    private String license;
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^(1[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{9}$",
    message = "手机号码格式错误")
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
