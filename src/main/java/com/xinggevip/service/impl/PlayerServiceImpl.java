package com.xinggevip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xinggevip.domain.Player;
import com.xinggevip.dao.PlayerMapper;
import com.xinggevip.service.PlayerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinggevip.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinggevip
 * @since 2021-04-11
 */
@Service
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper, Player> implements PlayerService {

    @Autowired
    private PlayerService playerService;

    @Override
    public HttpResult getPlayers(com.xinggevip.vo.Page page) {
        IPage<Player> playerIPage = playerService.lambdaQuery()
                .eq(Player::getActivateId, page.getActId())
                .like(Player::getName, page.getName())
                .page(new Page<>(page.getPageNum(), page.getPageSize()));
        HttpResult<IPage<Player>> httpResult = HttpResult.success(playerIPage);
        return httpResult;
    }

    @Override
    public  IPage<Player> findListByPage(Integer page, Integer pageCount){
        IPage<Player> wherePage = new Page<>(page, pageCount);
        Player where = new Player();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Player player){
        return baseMapper.insert(player);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Player player){
        return baseMapper.updateById(player);
    }

    @Override
    public Player findById(Long id){
        return  baseMapper.selectById(id);
    }
}
