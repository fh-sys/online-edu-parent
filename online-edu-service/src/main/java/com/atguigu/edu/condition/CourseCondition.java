package com.atguigu.edu.condition;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CourseVO
 * @Description: 课程条件查询vo
 * @Author Hao
 * @Date 2020/11/9 19:19
 * @Version V1.0
 **/
@ApiModel("课程分页按条件查询实体")
@Data
public class CourseCondition {

    @ApiModelProperty("课程标题")
    private String title;
    @ApiModelProperty(value = "视频状态 Draft未发布  Normal已发布")
    private String status;
}
