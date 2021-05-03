package com.xinggevip;

import com.google.gson.internal.$Gson$Preconditions;
import com.xinggevip.dao.ScorevalueMapper;
import com.xinggevip.domain.Scorevalue;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class RuihuadfApplicationTests {

    @Autowired
    private ScorevalueMapper scorevalueMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void test01() {
        Integer actid = 1;
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
    }



}
