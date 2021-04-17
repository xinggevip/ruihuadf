package com.xinggevip.controller;

import com.xinggevip.utils.HttpResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Scorevalue scorevalue){
        return scorevalueService.add(scorevalue);
    }

    @ApiOperation(value = "领导打分")
    @PostMapping("/dafen")
    public HttpResult dafen(@RequestBody List<Scorevalue> scorevalueList) {
        // 检查当前评委是否已经给他打过分了，没有打则存储，斗则抛出异常
        for (Scorevalue scorevalue : scorevalueList) {
            scorevalueService.add(scorevalue);
        }
        return HttpResult.success();
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
