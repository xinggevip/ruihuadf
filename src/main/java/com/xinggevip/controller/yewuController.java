package com.xinggevip.controller;

import com.xinggevip.dao.PlayerMapper;
import com.xinggevip.domain.Invitation;
import com.xinggevip.domain.User;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.exception.EmpLoginException;
import com.xinggevip.service.InvitationService;
import com.xinggevip.service.PlayerService;
import com.xinggevip.service.UserService;
import com.xinggevip.utils.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = {""})
@RestController
@RequestMapping("/user")
@CrossOrigin
public class yewuController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    @Resource
    private PlayerService playerService;

    @Resource
    private PlayerMapper playerMapper;


    @Resource
    private InvitationService invitationService;

    @ApiOperation(value = "进入打分活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invitationCode", value = "验证码"),
            @ApiImplicitParam(name = "name", value = "姓名")
    })
    @PostMapping("/login")
    public HttpResult login(@RequestParam String invitationCode,@RequestParam String name) {
        // 获取验证码类型
        List<Invitation> invitationList = invitationService.lambdaQuery().eq(Invitation::getInvitationCode, invitationCode).list();
        if (invitationList.size() == 0) {
            return HttpResult.failure(ResultCodeEnum.NOFIND_CODE_ERRORTWO);
        }

        String codeType = invitationList.get(0).getInvitationType();
        if ("1".equals(codeType)) {
            // 插入或更新选手
            Invitation invitation = new Invitation();


        }

        return null;

    }
}
