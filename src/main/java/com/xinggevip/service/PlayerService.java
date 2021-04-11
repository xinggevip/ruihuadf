package com.xinggevip.service;

import com.xinggevip.domain.Player;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinggevip
 * @since 2021-04-11
 */
public interface PlayerService extends IService<Player> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Player>
     */
    IPage<Player> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param player 
     * @return int
     */
    int add(Player player);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param player 
     * @return int
     */
    int updateData(Player player);

    /**
     * id查询数据
     *
     * @param id id
     * @return Player
     */
    Player findById(Long id);
}
