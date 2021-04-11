package com.xinggevip.service.impl;

import com.xinggevip.domain.Step;
import com.xinggevip.dao.StepMapper;
import com.xinggevip.service.StepService;
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
public class StepServiceImpl extends ServiceImpl<StepMapper, Step> implements StepService {

    @Override
    public  IPage<Step> findListByPage(Integer page, Integer pageCount){
        IPage<Step> wherePage = new Page<>(page, pageCount);
        Step where = new Step();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Step step){
        return baseMapper.insert(step);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Step step){
        return baseMapper.updateById(step);
    }

    @Override
    public Step findById(Long id){
        return  baseMapper.selectById(id);
    }
}
