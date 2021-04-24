package com.xinggevip.controller;

import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.utils.HttpResult;
import com.xinggevip.vo.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.xinggevip.service.ScoreitemService;
import com.xinggevip.domain.Scoreitem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/scoreitem")
@CrossOrigin
public class ScoreitemController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ScoreitemService scoreitemService;

    @ApiOperation(value = "根据环节ID获取打分项目列表")
    @PostMapping("/getScoreItems")
    public HttpResult getScoreItems(@RequestParam Long stepId) {
        List<Scoreitem> scoreitemList = scoreitemService.lambdaQuery()
                .eq(Scoreitem::getStepId, stepId)
                .list();
        if (scoreitemList.size() == 0) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.ITEM_EMPTY);
            return httpResult;
        }

        return HttpResult.success(scoreitemList);
    }


    @ApiOperation(value = "根据环节ID获取打分项目列表，去掉评价")
    @PostMapping("/onlyGetScoreItems")
    public HttpResult onlyGetScoreItems(@RequestParam Long stepId) {
        List<Scoreitem> scoreitemList = scoreitemService.lambdaQuery()
                .eq(Scoreitem::getStepId, stepId)
                .list()
                .stream()
                .filter(s -> s.getScoreType() == 1)
                .collect(Collectors.toList());
        if (scoreitemList.size() == 0) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.ITEM_EMPTY);
            return httpResult;
        }

        return HttpResult.success(scoreitemList);
    }


    @ApiOperation(value = "新增")
    @PostMapping()
    public HttpResult add(@RequestBody Scoreitem scoreitem){
        System.out.println(scoreitem);
        boolean b = scoreitem.insertOrUpdate();
        if (!b) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.ADD_ERROR);
            return httpResult;
        }

        // 根据stepid查一下有没有创建评价项目，没有则创建
        List<Scoreitem> list = scoreitemService.lambdaQuery()
                .eq(Scoreitem::getScoreType, 2)
                .eq(Scoreitem::getStepId,scoreitem.getStepId())
                .list();
        if (list.size() == 0) {
            Scoreitem scoreitem1 = new Scoreitem();
            scoreitem1.setItemName("评价");
            scoreitem1.setStepId(scoreitem.getStepId());
            scoreitem1.setScoreType(2);
            scoreitem1.insert();
        }

        HttpResult<Object> httpResult = HttpResult.success(ResultCodeEnum.DAFEN_SUCCESS);
        return httpResult;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public HttpResult delete(@PathVariable("id") Long id){
        int delete = scoreitemService.delete(id);
        if (delete == 0) {
            return HttpResult.failure(ResultCodeEnum.DELETE_ERROR);
        }
        return HttpResult.success(ResultCodeEnum.DELETE_SUCCESS);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Scoreitem scoreitem){
        return scoreitemService.updateData(scoreitem);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Scoreitem> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return scoreitemService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Scoreitem findById(@PathVariable Long id){
        return scoreitemService.findById(id);
    }

}
