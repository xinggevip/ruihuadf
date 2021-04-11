package com.xinggevip.service;

import com.xinggevip.domain.Invitation;
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
public interface InvitationService extends IService<Invitation> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Invitation>
     */
    IPage<Invitation> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param invitation 
     * @return int
     */
    int add(Invitation invitation);

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
     * @param invitation 
     * @return int
     */
    int updateData(Invitation invitation);

    /**
     * id查询数据
     *
     * @param id id
     * @return Invitation
     */
    Invitation findById(Long id);
}
