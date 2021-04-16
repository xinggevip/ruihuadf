package com.xinggevip.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("scoreitem")
@ApiModel(value="Scoreitem对象", description="")
public class Scoreitem extends Model<Scoreitem> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer stepId;

    private String itemName;

    private BigDecimal minScore;

    private BigDecimal maxScore;

    @ApiModelProperty(value = "1为数字打分，2星级打分")
    private Integer scoreType;

    private String strOne;

    private String strtwo;

    private String strthree;

    private BigDecimal numone;

    private BigDecimal numtwo;

    private BigDecimal numthree;


}
