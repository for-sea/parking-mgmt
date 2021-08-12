package com.forsea.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("账单实体类")
public class Bill {
    @ApiModelProperty(value = "账单id", example = "1")
    private Long bid;
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id", example = "1")
    private Long userId;
    @NotBlank(message = "车牌号不能为空")
    @ApiModelProperty("车牌号")
    private String license;
    @ApiModelProperty("进入时间")
    private String beginTime;
    @ApiModelProperty("离开时间")
    private String endTime;
    @ApiModelProperty("停车时间")
    private Integer stop;
    @ApiModelProperty("停车费")
    private Integer cost;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("更新时间")
    private String updateTime;
}
