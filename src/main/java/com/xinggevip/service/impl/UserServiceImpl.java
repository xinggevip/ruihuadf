package com.xinggevip.service.impl;

import com.xinggevip.domain.User;
import com.xinggevip.dao.UserMapper;
import com.xinggevip.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinggevip
 * @since 2021-04-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public  IPage<User> findListByPage(Integer page, Integer pageCount){
        IPage<User> wherePage = new Page<>(page, pageCount);
        User where = new User();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(User user){
        return baseMapper.insert(user);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(User user){
        return baseMapper.updateById(user);
    }

    @Override
    public User findById(Long id){
        return  baseMapper.selectById(id);
    }
}
