package com.forsea.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户VO")
public class UserVO {
    @ApiModelProperty(value = "用户id", example = "1")
    private Long uid;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("车牌号")
    private String license;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("用户状态")
    private Integer status;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("更新时间")
    private String updateTime;
}
