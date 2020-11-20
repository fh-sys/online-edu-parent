package com.atguigu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName TeacherVO
 * @Description: 讲师前端数据传输类
 * @Author Hao
 * @Date 2020/11/3 18:31
 * @Version V1.0
 **/
@ApiModel(description = "讲师条件查询数据传输类")
@Data
public class TeacherConditionVO {
    @ApiModelProperty(value = "讲师名称")
    private String name;
    @ApiModelProperty(value = "讲师级别")
    private Integer level;
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
