package com.xinggevip.service;

import com.xinggevip.domain.Scoreitem;
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
public interface ScoreitemService extends IService<Scoreitem> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Scoreitem>
     */
    IPage<Scoreitem> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param scoreitem 
     * @return int
     */
    int add(Scoreitem scoreitem);

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
     * @param scoreitem 
     * @return int
     */
    int updateData(Scoreitem scoreitem);

    /**
     * id查询数据
     *
     * @param id id
     * @return Scoreitem
     */
    Scoreitem findById(Long id);
}
