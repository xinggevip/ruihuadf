package com.xinggevip.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinggevip.domain.Scoreitem;
import com.xinggevip.domain.Step;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.service.ScoreitemService;
import com.xinggevip.service.StepService;
import com.xinggevip.utils.HttpResult;
import com.xinggevip.vo.ActPage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.xinggevip.service.ActivateService;
import com.xinggevip.domain.Activate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
@RequestMapping("/activate")
@CrossOrigin
public class ActivateController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ActivateService activateService;

    @Resource
    private StepService stepService;

    @Resource
    private ScoreitemService scoreitemService;

    @ApiOperation(value = "新增")
    @PostMapping()
    public HttpResult add(@RequestBody Activate activate){
        activate.setPushDate(new Date());
        boolean b = activate.insertOrUpdate();
        if (!b) {
            return HttpResult.failure(ResultCodeEnum.ACT_ADD_ERR);
        }
        return HttpResult.success(activate);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return activateService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public HttpResult update(@RequestBody Activate activate){
        // 如果这个活动已经创建完成过了，则不再更新步骤
        Activate activate2 = activate.selectById();
        if ("-1".equals(activate2.getStrtwo())) {
            activate.setStrtwo("-1");
        }
        // 前端要求进入最后一步，验证下添加打分项的那一步  是否每个环节都设置了打分项目
        if ("3".equals(activate.getStrtwo())) {

            if (activate.getId() == null) {
                return HttpResult.failure(ResultCodeEnum.PARAM_ERROR);
            }
            // 根据活动id获取所有的环节id，遍历环节id，根据环节id获取打分项目列表，过滤scoretype为1的，只要有一个打分项目列表为空就返回异常
            List<Step> stepList = stepService.lambdaQuery()
                    .eq(Step::getActivateId, activate.getId())
                    .list();
            for (Step step : stepList) {
                List<Scoreitem> collect = scoreitemService.lambdaQuery()
                        .eq(Scoreitem::getStepId, step.getId())
                        .list()
                        .stream()
                        .filter(s -> s.getScoreType() == 1)
                        .collect(Collectors.toList());
                if (collect.size() == 0) {
                    return HttpResult.failure(ResultCodeEnum.STEP_SCOREITEM_HAVE_EMPTY);
                }

            }

        }
        boolean b = activate.updateById();
        if (!b) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.UPDATE_ERROR);
            return httpResult;
        }
        Activate activate1 = activateService.findById(Long.valueOf(activate.getId()));
        if (activate1.getNumone() == null) {
            activate1.setNumone(BigDecimal.valueOf(0));
        }
        activate1.setNumone(BigDecimal.valueOf(activate1.getNumone().intValue() + 1));
        activate1.insertOrUpdate();
        return HttpResult.success(ResultCodeEnum.UPDATE_SUCCESS);
    }


    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @PostMapping("getActList")
    public HttpResult findListByPage(@RequestBody ActPage actPage) {

        if (actPage.getType() == null) {
            return HttpResult.failure(ResultCodeEnum.PARAM_ERROR);
        } else if (actPage.getType() == 1) { // 首页获取活动创建状态为已完成的活动
            IPage<Activate> activateIPage = activateService.lambdaQuery()
                    .eq(Activate::getStrtwo, "-1")
                    .eq(Activate::getStrthree,"1")
                    .like(Activate::getTitle,actPage.getValue())
                    .orderByDesc(Activate::getId)
                    .page(new Page<Activate>(actPage.getPage(), actPage.getPageCount()));
            return HttpResult.success(activateIPage);
        } else if (actPage.getType() == 2) { // 获取创建装填为草稿的活动
            IPage<Activate> activateIPage = activateService.lambdaQuery()
                    .eq(Activate::getUserId, actPage.getUserid())
                    .ne(Activate::getStrtwo, "-1")
                    .orderByDesc(Activate::getId)
                    .page(new Page<Activate>(actPage.getPage(), actPage.getPageCount()));
            return HttpResult.success(activateIPage);
        } else if (actPage.getType() == 3) { // 获取进行中的活动
            IPage<Activate> activateIPage = activateService.lambdaQuery()
                    .eq(Activate::getUserId, actPage.getUserid())
                    .ne(Activate::getStrone, "-1")
                    .ne(Activate::getStrone, "0")
                    .orderByDesc(Activate::getId)
                    .page(new Page<Activate>(actPage.getPage(), actPage.getPageCount()));
            return HttpResult.success(activateIPage);
        } else if (actPage.getType() == 4) { // 获取已结束的活动
            IPage<Activate> activateIPage = activateService.lambdaQuery()
                    .eq(Activate::getUserId, actPage.getUserid())
                    .eq(Activate::getStrone, "-1")
                    .orderByDesc(Activate::getId)
                    .page(new Page<Activate>(actPage.getPage(), actPage.getPageCount()));
            return HttpResult.success(activateIPage);
        } else if (actPage.getType() == 5) {
            IPage<Activate> activateIPage = activateService.lambdaQuery()
                    .eq(Activate::getUserId, actPage.getUserid())
                    .eq(Activate::getStrone, "0")
                    .eq(Activate::getStrtwo,"-1")
                    .orderByDesc(Activate::getId)
                    .page(new Page<Activate>(actPage.getPage(), actPage.getPageCount()));
            return HttpResult.success(activateIPage);
        }


        return HttpResult.failure(ResultCodeEnum.PARAM_ERROR);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public HttpResult findById(@PathVariable Long id){
        Activate activate = activateService.findById(id);
        HttpResult<Activate> httpResult = HttpResult.success(activate);
        return httpResult;
    }

}
