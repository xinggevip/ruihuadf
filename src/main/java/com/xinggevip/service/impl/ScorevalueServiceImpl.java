package com.xinggevip.service.impl;

import com.xinggevip.dao.StepMapper;
import com.xinggevip.domain.Scorevalue;
import com.xinggevip.dao.ScorevalueMapper;
import com.xinggevip.service.ScorevalueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinggevip.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinggevip
 * @since 2021-04-11
 */
@Service
public class ScorevalueServiceImpl extends ServiceImpl<ScorevalueMapper, Scorevalue> implements ScorevalueService {

    @Autowired
    private StepMapper stepMapper;

    @Override
    public  IPage<Scorevalue> findListByPage(Integer page, Integer pageCount){
        IPage<Scorevalue> wherePage = new Page<>(page, pageCount);
        Scorevalue where = new Scorevalue();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Scorevalue scorevalue){
        return baseMapper.insert(scorevalue);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Scorevalue scorevalue){
        return baseMapper.updateById(scorevalue);
    }

    @Override
    public Scorevalue findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public HttpResult getPlayerByStepIdAndJudgeId(Integer stepid, Integer judgeid,String playername) {
        List<Map<String, Object>> yidafenPlayers = stepMapper.getPlayerByStepIdAndJudgeId(stepid, judgeid, playername,null);
        return HttpResult.success(yidafenPlayers);
    }

    @Override
    public HttpResult getYiDaScoreValue(Integer stepid, Integer playerid, Integer judgeid) {
        List<Map<String, Object>> yidafenPlayerScoreValue = stepMapper.getYiDaScoreValue(stepid, playerid, judgeid);
        return HttpResult.success(yidafenPlayerScoreValue);
    }

    @Override
    public HttpResult getScoreOf0EveryOne(Integer actid) {

        return null;
    }
}
