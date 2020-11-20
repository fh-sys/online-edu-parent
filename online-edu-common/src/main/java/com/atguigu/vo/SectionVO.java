package com.atguigu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SectionVO
 * @Description: 小节数据传输实体
 * @Author Hao
 * @Date 2020/11/9 20:59
 * @Version V1.0
 **/
@ApiModel("小节数据传输实体")
@Data
public class SectionVO {

    @ApiModelProperty(value = "小节ID")
    private String id;

    @ApiModelProperty(value = "节点名称")
    private String title;

    @ApiModelProperty(value = "视频id")
    private String videoSourceId;

}
