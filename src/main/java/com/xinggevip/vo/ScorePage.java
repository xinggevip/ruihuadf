package com.xinggevip.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ACtPage对象", description="")
public class ScorePage {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动id")
    private Integer actid;

    @ApiModelProperty(value = "环节id")
    private String stepid;

    @ApiModelProperty(value = "第几页")
    private Integer page;

    @ApiModelProperty(value = "每页多少条")
    private Integer pageCount;

    @ApiModelProperty(value = "关键字")
    private String value;

    @ApiModelProperty(value = "获取类型")
    private Integer type;

    @ApiModelProperty(value = "单位关键字")
    private String company;

    @ApiModelProperty(value = "部门关键字")
    private String dep;

    @ApiModelProperty(value = "选手姓名")
    private String playername;

    @ApiModelProperty(value = "评委姓名")
    private String judgename;

}
