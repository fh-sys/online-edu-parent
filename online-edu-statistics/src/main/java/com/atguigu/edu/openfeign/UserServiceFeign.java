package com.atguigu.edu.openfeign;

import com.atguigu.response.RetVal;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName UserServiceFeign
 * @Description: 远程调用用户模块
 * @Author Hao
 * @Date 2020/11/14 16:27
 * @Version V1.0
 **/
@FeignClient(value = "EDU-USER")
public interface UserServiceFeign {
    @GetMapping("/member/center/countUserRegistByDay/{day}")
    public RetVal countUserRegistByDay(@PathVariable("day")String day);

    @GetMapping("/member/center/countUserLoginByDay/{day}")
    public RetVal countUserLoginByDay(@PathVariable("day")String day);
}
