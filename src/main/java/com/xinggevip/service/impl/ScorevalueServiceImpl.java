package com.xinggevip.service.impl;

import com.xinggevip.domain.Scorevalue;
import com.xinggevip.dao.ScorevalueMapper;
import com.xinggevip.service.ScorevalueService;
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
public class ScorevalueServiceImpl extends ServiceImpl<ScorevalueMapper, Scorevalue> implements ScorevalueService {

    @Override
    public  IPage<Scorevalue> findListByPage(Integer page, Integer pageCount){
        IPage<Scorevalue> wherePage = new Page<>(page, pageCount);
        Scorevalue where = new Scorevalue();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Scorevalue scorevalue){
        return baseMapper.insert(scorevalue);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Scorevalue scorevalue){
        return baseMapper.updateById(scorevalue);
    }

    @Override
    public Scorevalue findById(Long id){
        return  baseMapper.selectById(id);
    }
}
