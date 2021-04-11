package com.xinggevip.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-07 22:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CountInfo对象", description="")
public class CountInfo implements Serializable {

    @ApiModelProperty(value = "充值金额数量")
    private Integer chongMoneyNum;
    @ApiModelProperty(value = "充值金额")
    private BigDecimal chongMoney;

    @ApiModelProperty(value = "其他消费数量")
    private Integer otherMoneyNum;
    @ApiModelProperty(value = "其他消费")
    private BigDecimal otherMoney;

    @ApiModelProperty(value = "房间消费数量")
    private Integer roomMoneyNum;
    @ApiModelProperty(value = "房间消费")
    private BigDecimal roomMoney;

    @ApiModelProperty(value = "总消费数量")
    private Integer allMoneyNum;
    @ApiModelProperty(value = "总消费")
    private BigDecimal allMoney;

}
