package com.xinggevip.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@ApiModel(value="ACtPage对象", description="")
public class ActPage implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "第几页")
    private Integer page;

    @ApiModelProperty(value = "每页多少条")
    private Integer pageCount;

    @ApiModelProperty(value = "关键字")
    private String value;

    @ApiModelProperty(value = "获取类型")
    private Integer type;

    @ApiModelProperty(value = "环节ID")
    private Integer userid;


}
