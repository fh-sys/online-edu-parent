package com.atguigu.edu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SubjectImportVO
 * @Description: 分类导入数据传输对象
 * @Author Hao
 * @Date 2020/11/6 20:56
 * @Version V1.0
 **/
@ApiModel("分类导入数据传输对象")
@Data
public class SubjectImportVO {

    @ApiModelProperty(value = "父类别名称")
    private String parentSubjectName;

    @ApiModelProperty(value = "子类别名称")
    private String childSubjectName;




}
