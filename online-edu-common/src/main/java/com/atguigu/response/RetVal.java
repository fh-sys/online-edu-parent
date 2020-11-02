package com.atguigu.response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RetVal
 * @Description: 统一返回结果集对象
 * @Author Hao
 * @Date 2020/11/2 18:33
 * @Version V1.0
 **/
@ApiModel(description = "统一返回结果集")
@Data
public class RetVal {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回结果的状态码")
    private Integer code;
    @ApiModelProperty(value = "返回结果的信息")
    private String message;
    @ApiModelProperty(value = "返回的数据")
    private Map<String, Object> map = new HashMap<>();

    @ApiOperation(value = "成功")
    public static RetVal success(){
        RetVal retVal = new RetVal();
        retVal.setSuccess(true);
        retVal.setCode(20000);
        retVal.setMessage("成功");
        return retVal;
    }

    @ApiOperation(value = "失败")
    public static RetVal error(){
        RetVal retVal = new RetVal();
        retVal.setSuccess(false);
        retVal.setCode(20001);
        retVal.setMessage("失败");
        return retVal;
    }

    @ApiOperation(value = "设置返回的信息，链式调用")
    public RetVal message(String message){
        this.setMessage(message);
        return this;
    }

    @ApiOperation(value = "设置返回的状态码，链式调用")
    public RetVal code(Integer code){
        this.setCode(code);
        return this;
    }

    @ApiOperation(value = "设置返回单值数据，链式调用")
    public RetVal data(String key,Object value){
        this.map.put(key,value);
        return this;
    }

    @ApiOperation(value = "设置返回的键值数据")
    public RetVal data(Map<String, Object> map){
        this.setMap(map);
        return this;
    }

}
