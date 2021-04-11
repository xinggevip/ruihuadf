package com.xinggevip.service;

import com.xinggevip.domain.Activate;
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
public interface ActivateService extends IService<Activate> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Activate>
     */
    IPage<Activate> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param activate 
     * @return int
     */
    int add(Activate activate);

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
     * @param activate 
     * @return int
     */
    int updateData(Activate activate);

    /**
     * id查询数据
     *
     * @param id id
     * @return Activate
     */
    Activate findById(Long id);
}
