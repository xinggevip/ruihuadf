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
@TableName("player")
@ApiModel(value="Player对象", description="")
public class Player extends Model<Player> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer activateId;

    private String name;

    private String company;

    private String dep;

    private String post;

    private String sex;

    private String phone;

    private String profile;

    private String strone;

    private String strtwo;

    private String strthree;

    private BigDecimal numone;

    private BigDecimal numtwo;

    private BigDecimal numthree;


}
