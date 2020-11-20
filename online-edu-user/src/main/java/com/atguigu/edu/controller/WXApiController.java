package com.atguigu.edu.controller;

import com.atguigu.entity.MemberCenter;
import com.atguigu.edu.service.MemberCenterService;
import com.atguigu.edu.utils.HttpClientUtils;
import com.atguigu.edu.utils.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.Map;

/**
 * @ClassName WXApiController
 * @Description: 微信api控制器
 * @Author Hao
 * @Date 2020/11/17 20:48
 * @Version V1.0
 **/
@Api(tags = "微信接口的控制器")
@Controller
@RequestMapping("/api/ucenter/wx")
@CrossOrigin
public class WXApiController {

    @Value("${wx.open.app_id}")
    private String appId;
    @Value("${wx.open.redirect_url}")
    private String redirectUri;
    @Value("${wx.open.app_secret}")
    private String appSecret;


    @Autowired
    private MemberCenterService memberCenterService;

    @ApiOperation("微信登录接口")
    @GetMapping("/login")
    public String qrCode() throws Exception {
        //1.请求微信提供的接口获取code
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        String state ="atguigu";
        //用户请求该链接显示二维码
        String baseUrlFormat = String.format(baseUrl, appId, URLEncoder.encode(redirectUri, "UTF-8"), state);
        return "redirect:"+baseUrlFormat;
    }

    @ApiOperation("回调函数，通过code获取access_token")
    @GetMapping("callback")
    public String callback(String code) throws Exception {
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        String baseAccessTokenUrlFormat = String.format(baseAccessTokenUrl, appId, appSecret, code);
        String content = HttpClientUtils.get(baseAccessTokenUrlFormat);
        Gson gson = new Gson();
        /**
         * {
         * "access_token":"ACCESS_TOKEN",
         * "expires_in":7200,
         * "refresh_token":"REFRESH_TOKEN",
         * "openid":"OPENID",
         * "scope":"SCOPE",
         * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
         * }
         */
        Map<String, String> retMap = gson.fromJson(content, Map.class);
        String accessToken = retMap.get("access_token");
        String openId = retMap.get("openid");
        //根据access_toke和openId获取用户信息
        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                "?access_token=%s" +
                "&openid=%s";
        String userInfoUrlFormat = String.format(userInfoUrl, accessToken, openId);
        String wxUserInfo = HttpClientUtils.get(userInfoUrlFormat);
        Gson userInfoGson = new Gson();
        Map userInfoMap = userInfoGson.fromJson(wxUserInfo, Map.class);
        String nickname = (String) userInfoMap.get("nickname");
        String headimgurl = (String) userInfoMap.get("headimgurl");
        //gson默认数字转换为double
        Double sex = (Double) userInfoMap.get("sex");
        Integer sexDb = sex.intValue();
        //先根据openId查询用户是否登录过
        QueryWrapper<MemberCenter> memberCenterQueryWrapper = new QueryWrapper<>();
        memberCenterQueryWrapper.eq("openid",openId);
        MemberCenter existMember = memberCenterService.getOne(memberCenterQueryWrapper);
        if (existMember==null){
            existMember = new MemberCenter();
            existMember.setOpenid(openId);
            existMember.setNickname(nickname);
            existMember.setAvatar(headimgurl);
            existMember.setSex(sexDb);
            memberCenterService.save(existMember);
        }
        String token = JwtUtils.geneJsonWebToken(existMember);
//
        return "redirect:http://localhost:3000?token="+token;
    }


}
