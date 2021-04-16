package com.xinggevip.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinggevip.domain.Activate;
import com.xinggevip.domain.Scoreitem;
import com.xinggevip.domain.Scorevalue;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.service.ActivateService;
import com.xinggevip.service.ScoreitemService;
import com.xinggevip.utils.HttpResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.xinggevip.service.StepService;
import com.xinggevip.domain.Step;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@RequestMapping("/step")
@CrossOrigin
public class StepController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private StepService stepService;

    @Resource
    private ActivateService activateService;

    @Resource
    private ScoreitemService scoreitemService;

    // 根据活动ID获取当前环节的打分项
    @ApiOperation(value = "根据活动ID获取当前环节的打分项")
    @PostMapping("/currentScoreList")
    public HttpResult currentScoreList(@RequestParam Long actid){
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
        if ("-1".equals(strone)) {
            return HttpResult.failure(ResultCodeEnum.ACT_END);
        }

        Long currentStepId = Long.valueOf(strone);
        Step step = stepService.findById(currentStepId);
        Integer stepId = step.getId();
        List<Scoreitem> scoreitemList = scoreitemService.lambdaQuery().eq(Scoreitem::getStepId, stepId).list();
        return HttpResult.success(scoreitemList);
    }

    // 根据活动ID获取活动环节列表
    @ApiOperation(value = "根据活动ID获取活动环节列表")
    @PostMapping("/steplist")
    public HttpResult getStepByActID(@RequestParam Long actid) {
        List<Step> list = stepService.lambdaQuery().eq(Step::getActivateId, actid).list();
        if (list.size() == 0) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.STEP_EMPTY);
            return httpResult;
        }

        HttpResult<List<Step>> httpResult = HttpResult.success(list);
        return httpResult;
    }


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Step step){
        return stepService.add(step);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return stepService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Step step){
        return stepService.updateData(step);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Step> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return stepService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public HttpResult findById(@PathVariable Long id){
        Step step = stepService.findById(id);
        if (step == null) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.NOFIND_STEP_ERR);
            return httpResult;
        }
        HttpResult<Step> httpResult = HttpResult.success(step);
        return httpResult;
    }

    @ApiOperation(value = "id查询当前环节")
    @PostMapping("/currentStep")
    public HttpResult currentStep(@RequestParam Long id){
        Activate activate = activateService.findById(id);
        String strone = activate.getStrone();
        Long currentStepId = Long.valueOf(strone);
        Step step = stepService.findById(currentStepId);
        if (step == null) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.NOFIND_STEP_ERR);
            return httpResult;
        }
        HttpResult<Step> httpResult = HttpResult.success(step);
        return httpResult;
    }

}
