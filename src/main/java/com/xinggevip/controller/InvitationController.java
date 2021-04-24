package com.xinggevip.controller;

import com.xinggevip.utils.HttpResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.xinggevip.service.InvitationService;
import com.xinggevip.domain.Invitation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xinggevip
 * @since 2021-04-11
 */
@Api(tags = {""})
@RestController
@RequestMapping("/invitation")
@CrossOrigin
public class InvitationController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private InvitationService invitationService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Invitation invitation){
        return invitationService.add(invitation);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return invitationService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Invitation invitation){
        return invitationService.updateData(invitation);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Invitation> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return invitationService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Invitation findById(@PathVariable Long id){
        return invitationService.findById(id);
    }

    // 根据互动id获取验证码
    @ApiOperation(value = "根据互动id获取验证码")
    @GetMapping("/getInvitationsByActId/{id}")
    public HttpResult getInvitationsByActId(@PathVariable Integer id){
        List<Invitation> invitationList = invitationService.lambdaQuery().eq(Invitation::getActivateId, id).list();
        if (invitationList.size() == 0) {
            ArrayList<Invitation> invitations = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Invitation invitation = new Invitation();
                invitation.setActivateId(id);
                invitation.setInvitationType(String.valueOf(i + 1));
                double x = Math.random();
                double min = 1000;
                double max = 9999;
                double y = x * (max - min) + min;
                long n = (long) y;
                System.out.println(n);
                invitation.setInvitationCode(String.valueOf(n));
                invitation.insert();
                invitations.add(invitation);
            }
            return HttpResult.success(invitations);
        }
        return HttpResult.success(invitationList);
    }


}
