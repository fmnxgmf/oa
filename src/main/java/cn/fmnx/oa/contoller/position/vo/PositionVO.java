package cn.fmnx.oa.contoller.position.vo;

import cn.fmnx.oa.contoller.dept.vo.DeptIdAndNameVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName PositionVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/13
 * @Version V1.0
 **/
@Data
@ApiModel(description = "职位表数据显示")
public class PositionVO implements Serializable {

    private static final long serialVersionUID = -1342274209112866842L;
    /**
     * position_id
     * level` int(
     * name` varch
     * describtion
     * deptid` big
     */
    @ApiModelProperty(name = "positionId",value = "职位id")
    private Long positionId;
    @ApiModelProperty(value = "职位层级",name = "level")
    private Integer level;
    @ApiModelProperty(name = "name",value = "职位名字")
    private String name;
    @ApiModelProperty(name = "describtion",value = "该职位的描述信息")
    private String describtion;
//    @ApiModelProperty(name = "deptId",value = "该职位对应的部门id")
//    private Long deptId;
    private DeptIdAndNameVO deptIdAndNameVO;
}
