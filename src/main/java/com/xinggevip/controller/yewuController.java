package com.xinggevip.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.xinggevip.dao.PlayerMapper;
import com.xinggevip.domain.Invitation;
import com.xinggevip.domain.Judge;
import com.xinggevip.domain.Player;
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
import java.util.HashMap;
import java.util.List;

@Api(tags = {""})
@RestController
@RequestMapping("/yewu")
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
    @PostMapping("/join")
    public HttpResult login(@RequestParam String invitationCode,@RequestParam String name) {
        // 获取验证码类型
        List<Invitation> invitationList = invitationService.lambdaQuery().eq(Invitation::getInvitationCode, invitationCode).list();
        if (invitationList.size() == 0) {
            return HttpResult.failure(ResultCodeEnum.NOFIND_CODE_ERRORTWO);
        }

        Invitation invitation = invitationList.get(0);
        String codeType = invitation.getInvitationType();
        Integer activateId = invitation.getActivateId();
        // 验证码为选手
        if ("1".equals(codeType)) {
            // 插入或更新选手
            QueryWrapper<Player> wrapper = new QueryWrapper<>();
            wrapper.eq("name", name);
            Player player1 = new Player();
            Player player2 = player1.selectOne(wrapper);
            if (player2 == null) {
                Player player = new Player();
                player.setName(name);
                player.setActivateId(activateId);
                boolean b = player.insertOrUpdate();
                System.out.println("验证码为选手，insertOrUpdate的结果"+String.valueOf(b));
            }
        }
        // 验证码为评委
        if ("2".equals(codeType)) {

            QueryWrapper<Judge> wrapper = new QueryWrapper<>();
            wrapper.eq("judge_name", name);
            Judge judge1 = new Judge();
            Judge judge2 = judge1.selectOne(wrapper);
            if (judge2 == null) {
                Judge judge = new Judge();
                judge.setJudgeName(name);
                judge.setActivateId(activateId);
                boolean b = judge.insertOrUpdate();
                System.out.println("验证码为评委，insertOrUpdate的结果"+String.valueOf(b));
            }


        }
        // 验证码为场控
        if ("3".equals(codeType)) {
            System.out.println("验证码为场控");
        }

        HashMap<Object, Object> map = new HashMap<>();
        map.put("name",name);
        map.put("codeType", codeType);
        HttpResult<Object> httpResult = HttpResult.success(map);

        return httpResult;

    }
}
