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


    @ApiOperation(value = "新增")
    @PostMapping()
    public HttpResult add(@RequestBody Scoreitem scoreitem){
        int add = scoreitemService.add(scoreitem);
        if (add == 0) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.DAFEN_ERR);
            return httpResult;
        }
        HttpResult<Object> httpResult = HttpResult.success(ResultCodeEnum.DAFEN_SUCCESS);
        return httpResult;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return scoreitemService.delete(id);
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
