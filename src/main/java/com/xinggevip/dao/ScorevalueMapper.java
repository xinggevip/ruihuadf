package com.xinggevip.dao;

import com.xinggevip.domain.Scorevalue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xinggevip
 * @since 2021-04-11
 */
public interface ScorevalueMapper extends BaseMapper<Scorevalue> {
    List<Map<String,Object>> getScoreOfEveryOne(@Param("actid") Integer actid);
}
