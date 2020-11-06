package com.atguigu.handler;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyRuntimeException
 * @Description: 自定义异常
 * @Author Hao
 * @Date 2020/11/3 21:26
 * @Version V1.0
 **/
@ApiModel("这是一个自定义异常")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyRuntimeException extends RuntimeException {

    @ApiModelProperty(value = "异常状态码")
    private Integer code;
    @ApiModelProperty(value = "异常信息")
    private String message;
}
