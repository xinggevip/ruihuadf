package com.xinggevip.controller;

import com.xinggevip.dao.StepMapper;
import com.xinggevip.domain.Scoreitem;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.service.ScoreitemService;
import com.xinggevip.utils.HttpResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xinggevip.service.ScorevalueService;
import com.xinggevip.domain.Scorevalue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
@RequestMapping("/scorevalue")
@CrossOrigin
public class ScorevalueController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ScorevalueService scorevalueService;

    @Resource
    private ScoreitemService scoreitemService;

    @Autowired
    private StepMapper stepMapper;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Scorevalue scorevalue){
        return scorevalueService.add(scorevalue);
    }

    @ApiOperation(value = "领导打分")
    @PostMapping("/dafen")
    public HttpResult dafen(@RequestBody List<Scorevalue> scorevalueList) {
        // 检查当前评委是否已经给他打过分了，没有打则存储，否则抛出异常
        if (scorevalueList.size() == 0) {
            return HttpResult.failure(ResultCodeEnum.DAFEN_ITEM_EMPTY);
        }

        Scorevalue scorevalue1 = scorevalueList.get(0);
        Scoreitem scoreitem = scoreitemService.lambdaQuery()
                .eq(Scoreitem::getId, scorevalue1.getScoreitemId())
                .list()
                .get(0);
        List<Map<String, Object>> yidafenPlayers = stepMapper.getPlayerByStepIdAndJudgeId(scoreitem.getStepId(), scorevalue1.getJudgeId(), null,scorevalue1.getPlayerId());
        if (yidafenPlayers.size() > 0) {
            return HttpResult.failure(ResultCodeEnum.DOUBLE_DAFWN_ERR);
        }

        String uuid = UUID.randomUUID().toString();
        for (Scorevalue scorevalue : scorevalueList) {
            scorevalue.setStrone(uuid);
            scorevalueService.add(scorevalue);
        }
        return HttpResult.success();
    }

    // 根据环节ID和选手ID和评委ID查成绩
    @ApiOperation(value = "根据环节ID和选手ID和评委ID查车成绩")
    @PostMapping("getYiDaScoreValue")
    public HttpResult getYiDaScoreValue(@RequestParam Integer stepid, @RequestParam Integer playerid, @RequestParam Integer judgeid) {
        return scorevalueService.getYiDaScoreValue(stepid, playerid, judgeid);
    }


    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return scorevalueService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Scorevalue scorevalue){
        return scorevalueService.updateData(scorevalue);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Scorevalue> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return scorevalueService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Scorevalue findById(@PathVariable Long id){
        return scorevalueService.findById(id);
    }

}
