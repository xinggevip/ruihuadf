package com.xinggevip.service;

import com.xinggevip.domain.Scorevalue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinggevip.utils.HttpResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinggevip
 * @since 2021-04-11
 */
public interface ScorevalueService extends IService<Scorevalue> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Scorevalue>
     */
    IPage<Scorevalue> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param scorevalue 
     * @return int
     */
    int add(Scorevalue scorevalue);

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
     * @param scorevalue 
     * @return int
     */
    int updateData(Scorevalue scorevalue);

    /**
     * id查询数据
     *
     * @param id id
     * @return Scorevalue
     */
    Scorevalue findById(Long id);

    HttpResult getPlayerByStepIdAndJudgeId(Integer stepid, Integer judgeid,String playername);

    HttpResult getYiDaScoreValue(Integer stepid, Integer playerid, Integer judgeid);

    HttpResult getScoreOf0EveryOne(Integer actid);
}
