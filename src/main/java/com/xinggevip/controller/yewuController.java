package com.xinggevip.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.xinggevip.dao.PlayerMapper;
import com.xinggevip.domain.*;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.exception.EmpLoginException;
import com.xinggevip.service.ActivateService;
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

    @Resource
    private ActivateService activateService;

    @ApiOperation(value = "进入打分活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invitationCode", value = "验证码"),
            @ApiImplicitParam(name = "name", value = "姓名"),
            @ApiImplicitParam(name = "actid", value = "活动id")
    })
    @PostMapping("/join")
    public HttpResult login(@RequestParam String invitationCode,@RequestParam String name, @RequestParam String actid) {
        // 获取验证码类型
        List<Invitation> invitationList = invitationService.lambdaQuery()
                .eq(Invitation::getActivateId,actid)
                .eq(Invitation::getInvitationCode, invitationCode)
                .list();
        if (invitationList.size() == 0) {
            return HttpResult.failure(ResultCodeEnum.NOFIND_CODE_ERRORTWO);
        }

        Invitation invitation = invitationList.get(0);
        String codeType = invitation.getInvitationType();
        Integer activateId = Integer.valueOf(actid);
        Integer userid = null;
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
                if (b) {
                    QueryWrapper<Player> wrapper1 = new QueryWrapper<>();
                    wrapper.eq("activate_id", activateId).eq("name", name);
                    Player player3 = new Player();
                    Player player4 = player3.selectOne(wrapper1);
                    userid = player4.getId();
                }
                System.out.println("验证码为选手，insertOrUpdate的结果"+String.valueOf(b));
            }else{
                userid = player2.getId();
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
                if (b) {
                    QueryWrapper<Judge> wrapper1 = new QueryWrapper<>();
                    wrapper1.eq("activate_id", activateId).eq("judge_name", name);
                    Judge judge3 = new Judge();
                    Judge judge4 = judge3.selectOne(wrapper1);
                    userid = judge4.getId();
                }
                System.out.println("验证码为评委，insertOrUpdate的结果"+String.valueOf(b));
            }else{
                userid = judge2.getId();
            }


        }
        // 验证码为场控
        if ("3".equals(codeType)) {
            userid = -1;
        }

        HashMap<Object, Object> map = new HashMap<>();
        map.put("userid", userid);
        map.put("name",name);
        map.put("codeType", codeType);
        HttpResult<Object> httpResult = HttpResult.success(map);

        return httpResult;

    }
}
