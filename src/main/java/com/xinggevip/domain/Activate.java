package com.xinggevip.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@TableName("activate")
@ApiModel(value="Activate对象", description="")
public class Activate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String title;

    private String profile;

    private String bgurl;

    private Date pushDate;

    private Date startDate;

    private Date endDate;

    private String anonymous;

    private String strone;

    private String strtwo;

    private String strthree;

    private BigDecimal numone;

    private BigDecimal numtwo;

    private BigDecimal numthree;


}
