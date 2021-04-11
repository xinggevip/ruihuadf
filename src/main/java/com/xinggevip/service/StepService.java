package com.xinggevip.service;

import com.xinggevip.domain.Step;
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
public interface StepService extends IService<Step> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Step>
     */
    IPage<Step> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param step 
     * @return int
     */
    int add(Step step);

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
     * @param step 
     * @return int
     */
    int updateData(Step step);

    /**
     * id查询数据
     *
     * @param id id
     * @return Step
     */
    Step findById(Long id);
}
