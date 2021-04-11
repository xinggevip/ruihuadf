package com.xinggevip.controller;

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


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Scoreitem scoreitem){
        return scoreitemService.add(scoreitem);
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
