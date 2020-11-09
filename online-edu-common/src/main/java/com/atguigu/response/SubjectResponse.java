package com.atguigu.response;

import com.sun.org.apache.bcel.internal.generic.NEW;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SubjectResponse
 * @Description: 课程返回结果
 * @Author Hao
 * @Date 2020/11/7 11:02
 * @Version V1.0
 **/
@ApiModel(description = "课程分类的返回结果")
@Data
public class SubjectResponse {

    /**
     * {
     *           id: 1,
     *           label: '一级 1',
     *           children: [{
     *             id: 4,
     *             label: '二级 1-1',
     *             children: [{
     *               id: 9,
     *               label: '三级 1-1-1'
     *             }, {
     *               id: 10,
     *               label: '三级 1-1-2'
     *             }]
     *           }]
     *         }
     */
    @ApiModelProperty("分类id")
    private String id;
    @ApiModelProperty("分类名称")
    private String title;
    @ApiModelProperty("子分类列表")
    private List<SubjectResponse>  children = new ArrayList<>();
}
