package com.xinggevip.controller;

import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.exception.EmpLoginException;
import com.xinggevip.utils.HttpResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.xinggevip.service.UserService;
import com.xinggevip.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

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
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping("/login")
    public HttpResult login(@RequestBody User user) {
        List<User> userList = userService.lambdaQuery().eq(User::getPhone, user.getPhone()).eq(User::getPassword, user.getPassword()).list();
        int size = userList.size();
        if (size != 1) {
            throw new EmpLoginException();
        }
        User user1 = userList.get(0);
        HttpResult<User> empHttpResult = new HttpResult<>(user1);
        return empHttpResult;

    }

    @ApiOperation(value = "新增")
    @PostMapping()
    public HttpResult add(@RequestBody User user){
        List<User> userList = userService.lambdaQuery().eq(User::getPhone, user.getPhone()).list();
        if (userList.size() != 0 ) {
            if (userList.get(0).getPhone().equals(user.getPhone())) {
                HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.REG_ERRORTWO);
                return httpResult;
            }
        }
        int res = userService.add(user);
        if (res != 1) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.REG_ERROR);
            return httpResult;
        }
        return HttpResult.success(ResultCodeEnum.REG_SUCCESS);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return userService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public HttpResult update(@RequestBody User user){
        boolean b = userService.updateById(user);
        if (b) {
            HttpResult<User> userHttpResult = new HttpResult<>(ResultCodeEnum.UPDATE_SUCCESS, user, Boolean.TRUE);
            return userHttpResult;
        }
        HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.UPDATE_ERROR);
        return httpResult;
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<User> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return userService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public HttpResult findById(@PathVariable Long id){
        return HttpResult.success(userService.findById(id));
    }

}
