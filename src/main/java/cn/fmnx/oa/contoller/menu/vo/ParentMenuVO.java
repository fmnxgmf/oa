package cn.fmnx.oa.contoller.menu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ParentMenuVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/11
 * @Version V1.0
 **/
@Data
@ApiModel(description = "新增菜单中父级菜单的model对象，前端用name展示，给后端需要是id值")
public class ParentMenuVO {
    @ApiModelProperty(name = "parentId",value = "新增菜单父级菜单的id值")
    private Long parentId;
    @ApiModelProperty(name = "name",value = "新增菜单中父级菜单在前端展示的名字，传入后端需要的是id值")
    private String parentName;
}
