package com.xinggevip.service.impl;

import com.xinggevip.domain.Invitation;
import com.xinggevip.dao.InvitationMapper;
import com.xinggevip.service.InvitationService;
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
public class InvitationServiceImpl extends ServiceImpl<InvitationMapper, Invitation> implements InvitationService {

    @Override
    public  IPage<Invitation> findListByPage(Integer page, Integer pageCount){
        IPage<Invitation> wherePage = new Page<>(page, pageCount);
        Invitation where = new Invitation();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Invitation invitation){
        return baseMapper.insert(invitation);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Invitation invitation){
        return baseMapper.updateById(invitation);
    }

    @Override
    public Invitation findById(Long id){
        return  baseMapper.selectById(id);
    }
}
