package com.xinggevip.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-03 13:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Page对象", description="")
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "第几页")
    private Integer pageNum;

    @ApiModelProperty(value = "每页多少条")
    private Integer pageSize;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "活动ID")
    private Long actId;

    @ApiModelProperty(value = "环节ID")
    private Long stepId;


}
