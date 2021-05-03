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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Autowired
    private ScorevalueMapper scorevalueMapper;

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
        List<Map<String, Object>> res = scorevalueMapper.getScoreOfEveryOne(actid);
        ArrayList<Map<String, Object>> quchonghou = new ArrayList<>();

        for (Map<String, Object> re : res) {
//            System.out.println(re);
            Integer flag = 0;
            Integer activateIdBySql = (Integer) re.get("activate_id");
            Integer stepIdBySql = (Integer) re.get("step_id");
            Integer playerIdBySql = (Integer) re.get("player_id");
            BigDecimal sum_score = (BigDecimal) re.get("sum_score");
            for (Map<String, Object> quchong : quchonghou) {
                Integer activate_id = (Integer) quchong.get("activate_id");
                Integer step_id = (Integer) quchong.get("step_id");
                Integer player_id = (Integer) quchong.get("player_id");
                if (activateIdBySql.equals(activate_id) && stepIdBySql.equals(step_id) && playerIdBySql.equals(player_id)) {
                    flag = 1;
                    // 判断是否存在judge_num字段
                    System.out.println("带去重中存在，累加和");
                    System.out.println(quchong);
                    boolean cunzaiRes = quchong.containsKey("judge_num");
                    if (cunzaiRes) {
                        quchong.put("judge_num", (Integer) quchong.get("judge_num") + 1);
                    }
                    BigDecimal sum_score1 = (BigDecimal)quchong.get("sum_score");
                    quchong.put("sum_score", sum_score1.add(sum_score));
                    break;

                }
            }

            if (flag == 0) {
                System.out.println("不存在，添加");
                System.out.println(re);
                re.put("judge_num", 1);
                quchonghou.add(re);
            }

        }


        System.out.println("====================================");

        for (Map<String, Object> map : quchonghou) {
            System.out.println(map);
        }

        List<Map<String, Object>> mapList = quchonghou.stream().peek(m -> {
            BigDecimal sum_score = (BigDecimal) m.get("sum_score");
            Integer judge_num = (Integer) m.get("judge_num");
            m.put("avg_score_sum", sum_score.divide(BigDecimal.valueOf(judge_num)));
        }).collect(Collectors.toList());

        System.out.println("=============最终处理结果=================");
        for (Map<String, Object> stringObjectMap : mapList) {
            System.out.println(stringObjectMap);
        }
        return HttpResult.success(mapList);
    }
}
