package com.xinggevip.service.impl;

import com.xinggevip.domain.Activate;
import com.xinggevip.dao.ActivateMapper;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.service.ActivateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinggevip.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ActivateServiceImpl extends ServiceImpl<ActivateMapper, Activate> implements ActivateService {

    @Override
    public  IPage<Activate> findListByPage(Integer page, Integer pageCount){
        IPage<Activate> wherePage = new Page<>(page, pageCount);
        Activate where = new Activate();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Activate activate){
        return baseMapper.insert(activate);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Activate activate){
        return baseMapper.updateById(activate);
    }

    @Override
    public Activate findById(Long id){
        return  baseMapper.selectById(id);
    }
}
