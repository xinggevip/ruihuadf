package com.xinggevip.service;

import com.xinggevip.domain.Judge;
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
public interface JudgeService extends IService<Judge> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Judge>
     */
    IPage<Judge> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param judge 
     * @return int
     */
    int add(Judge judge);

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
     * @param judge 
     * @return int
     */
    int updateData(Judge judge);

    /**
     * id查询数据
     *
     * @param id id
     * @return Judge
     */
    Judge findById(Long id);
}
