package com.xinggevip.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xinggevip
 * @since 2021-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("scorevalue")
@ApiModel(value="Scorevalue对象", description="")
public class Scorevalue implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer scoreitemId;

    private Integer judgeId;

    private Integer playerId;

    @ApiModelProperty(value = "数字打分")
    private BigDecimal numValue;

    @ApiModelProperty(value = "文字评价")
    private String strValue;

    private String strone;

    private String strtwo;

    private String strthree;

    private BigDecimal numone;

    private BigDecimal numtwo;

    private BigDecimal numthree;


}
