package cn.fmnx.oa.entity.menu;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName SystemTypeList
 * @Description: 菜单栏类型管理
 * @Author gmf
 * @Date 2020/2/10
 * @Version V1.0
 **/
@Data
@Table(name = "aoa_type_list")
public class SystemTypeList {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "type_id")
    /**
     * 类型id
     */
    private Long typeId;
    /**
     *类型名字
     */
    @Column(name = "type_name")
    @NotBlank(message = "类型名称不能为空")
    private String typeName;
    /**
     *排序值
     */
    @Column(name="sort_value")
    private Integer typeSortValue;
    /**
     *所属模块
     */
    @Column(name="type_model")
    private String typeModel;
    /**
     *类型颜色
     */
    @Column(name="type_color")
    private String typeColor;
}
