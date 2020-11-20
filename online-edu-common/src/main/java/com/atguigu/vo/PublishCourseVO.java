package com.atguigu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName PublicCourseVO
 * @Description: 发布课程数据传输实体
 * @Author Hao
 * @Date 2020/11/11 20:58
 * @Version V1.0
 **/
@ApiModel("发布课程实体")
@Data
public class PublishCourseVO {
    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "讲师")
    private String teacherName;

    @ApiModelProperty(value = "课程标题")
    private String courseName;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "一级分类名称")
    private String parentSubjectName;

    @ApiModelProperty(value = "一级分类名称")
    private String childSubjectName;
}
