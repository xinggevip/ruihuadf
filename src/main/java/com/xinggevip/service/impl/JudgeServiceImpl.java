package com.xinggevip.service.impl;

import com.xinggevip.domain.Judge;
import com.xinggevip.dao.JudgeMapper;
import com.xinggevip.service.JudgeService;
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
public class JudgeServiceImpl extends ServiceImpl<JudgeMapper, Judge> implements JudgeService {

    @Override
    public  IPage<Judge> findListByPage(Integer page, Integer pageCount){
        IPage<Judge> wherePage = new Page<>(page, pageCount);
        Judge where = new Judge();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Judge judge){
        return baseMapper.insert(judge);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Judge judge){
        return baseMapper.updateById(judge);
    }

    @Override
    public Judge findById(Long id){
        return  baseMapper.selectById(id);
    }
}
