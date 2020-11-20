package com.atguigu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChapterVO
 * @Description: 章节数据传输类
 * @Author Hao
 * @Date 2020/11/9 20:55
 * @Version V1.0
 **/
@ApiModel("章节数据传输实体")
@Data
public class ChapterVO {
    @ApiModelProperty(value = "章节ID")
    private String id;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "章节小节")
    private List<SectionVO> sectionVOList = new ArrayList<>();

}
