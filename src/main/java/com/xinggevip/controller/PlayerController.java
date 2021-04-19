package com.xinggevip.controller;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.xinggevip.domain.Activate;
import com.xinggevip.domain.Scorevalue;
import com.xinggevip.domain.Step;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.service.ActivateService;
import com.xinggevip.service.ScorevalueService;
import com.xinggevip.service.StepService;
import com.xinggevip.utils.HttpResult;
import com.xinggevip.vo.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.xinggevip.service.PlayerService;
import com.xinggevip.domain.Player;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xinggevip
 * @since 2021-04-11
 */
@Api(tags = {""})
@RestController
@RequestMapping("/player")
@CrossOrigin
public class PlayerController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private PlayerService playerService;

    @Resource
    private ScorevalueService scorevalueService;

    @Resource
    private ActivateService activateService;

    @Resource
    private StepService stepService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Player player) {
        return playerService.add(player);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id) {
        return playerService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public HttpResult update(@RequestBody Player player) {
        if (playerService.updateData(player) == 0) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.UPDATE_ERROR);
            return httpResult;
        }
        HttpResult<Object> httpResult = HttpResult.success(ResultCodeEnum.UPDATE_SUCCESS);
        return httpResult;
    }

    @ApiOperation(value = "根据活动ID更新所有选手的上台状态")
    @PostMapping("/updateAllPlayersStatus")
    public HttpResult UpdateAllPlayersStatus(@RequestParam String actid,@RequestParam String status) {
        if ("on".equals(status)) {
            playerService.lambdaUpdate().eq(Player::getActivateId, actid).set(Player::getStrone, "1").update();
        }

        if ("down".equals(status)) {
            playerService.lambdaUpdate().eq(Player::getActivateId, actid).set(Player::getStrone, "0").update();
        }

        return HttpResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Player> findListByPage(@RequestParam Integer page,
                                        @RequestParam Integer pageCount) {
        return playerService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "根据活动ID关键字和分页信息获取")
    @PostMapping("/getPlayers")
    public HttpResult getPlayers(@RequestBody Page page) {
        return playerService.getPlayers(page);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public HttpResult findById(@PathVariable Long id) {
        Player player = playerService.findById(id);
        if (player == null) {
            return HttpResult.failure(ResultCodeEnum.NO_FIND_USER_BY_ID);
        }
        return HttpResult.success(playerService.findById(id));
    }

    @ApiOperation(value = "根据环节id，评委id得到该评委已打分的选手")
    @PostMapping("/findYidafenPlayers")
    public HttpResult findYidafenPlayers(@RequestParam Long actid, @RequestParam Integer judgeid, @RequestParam String playername) {

        // 根据活动ID获取当前环节ID
        Activate activate = activateService.findById(actid);
        String strone = activate.getStrone();
        // 活动状态为空返回服务器异常
        if (StringUtils.isEmpty(strone)) {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR);
        }
        // 0为未开始
        if ("0".equals(strone)) {
            return HttpResult.failure(ResultCodeEnum.ACT_NOT_START);
        }
        // -1为已结束
//        if ("-1".equals(strone)) {
//            return HttpResult.failure(ResultCodeEnum.ACT_END);
//        }

        Long currentStepId = Long.valueOf(strone);
        Step step = stepService.findById(currentStepId);
        Integer stepid = step.getId();
        return scorevalueService.getPlayerByStepIdAndJudgeId(stepid, judgeid, playername);
    }
}
