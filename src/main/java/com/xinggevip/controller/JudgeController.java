package com.xinggevip.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.xinggevip.service.JudgeService;
import com.xinggevip.domain.Judge;
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
@RequestMapping("/judge")
@CrossOrigin
public class JudgeController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private JudgeService judgeService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Judge judge){
        return judgeService.add(judge);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return judgeService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Judge judge){
        return judgeService.updateData(judge);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Judge> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return judgeService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Judge findById(@PathVariable Long id){
        return judgeService.findById(id);
    }

}
