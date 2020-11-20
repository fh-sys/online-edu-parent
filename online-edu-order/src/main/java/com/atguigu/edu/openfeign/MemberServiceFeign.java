package com.atguigu.edu.openfeign;

import com.atguigu.response.RetVal;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName MemberServiceFeign
 * @Description:
 * @Author Hao
 * @Date 2020/11/18 21:07
 * @Version V1.0
 **/
@FeignClient(value = "EDU-USER")
public interface MemberServiceFeign {

    @GetMapping("/member/center/getMemberById/{userId}")
    public RetVal getMemberById(@PathVariable("userId")String userId);
}
