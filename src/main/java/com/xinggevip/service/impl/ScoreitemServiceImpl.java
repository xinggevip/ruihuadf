package com.xinggevip.service.impl;

import com.xinggevip.domain.Scoreitem;
import com.xinggevip.dao.ScoreitemMapper;
import com.xinggevip.service.ScoreitemService;
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
public class ScoreitemServiceImpl extends ServiceImpl<ScoreitemMapper, Scoreitem> implements ScoreitemService {

    @Override
    public  IPage<Scoreitem> findListByPage(Integer page, Integer pageCount){
        IPage<Scoreitem> wherePage = new Page<>(page, pageCount);
        Scoreitem where = new Scoreitem();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Scoreitem scoreitem){
        return baseMapper.insert(scoreitem);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Scoreitem scoreitem){
        return baseMapper.updateById(scoreitem);
    }

    @Override
    public Scoreitem findById(Long id){
        return  baseMapper.selectById(id);
    }
}
