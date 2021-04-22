package com.xinggevip.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.utils.HttpResult;
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
        int i = activateService.updateData(activate);
        if (i == 0) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.UPDATE_ERROR);
            return httpResult;
        }
        Activate activate1 = activateService.findById(Long.valueOf(activate.getId()));
        activate1.setNumone(BigDecimal.valueOf(activate1.getNumone().intValue() + 1));
        activate1.insertOrUpdate();
        return HttpResult.success(ResultCodeEnum.UPDATE_SUCCESS);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public HttpResult findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        HttpResult<IPage<Activate>> httpResult = new HttpResult<>(activateService.lambdaQuery()
                .eq(Activate::getStrtwo,"-1")
                .orderByDesc(Activate::getId)
                .page(new Page<Activate>(page, pageCount)));
        return httpResult;
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public HttpResult findById(@PathVariable Long id){
        Activate activate = activateService.findById(id);
        HttpResult<Activate> httpResult = HttpResult.success(activate);
        return httpResult;
    }

}
