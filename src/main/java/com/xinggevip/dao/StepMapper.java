package com.xinggevip.dao;

import com.xinggevip.domain.Step;
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
public interface StepMapper extends BaseMapper<Step> {
    List<Map<String, Object>> getPlayerByStepIdAndJudgeId(
            @Param("stepid") Integer stepid,
            @Param("judgeid") Integer judgeid,
            @Param("playername") String playername,
            @Param("playerid") Integer playerid
    );
}
