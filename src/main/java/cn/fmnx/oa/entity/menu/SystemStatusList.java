package cn.fmnx.oa.entity.menu;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName SystemStatusList
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/12
 * @Version V1.0
 **/
@Data
@Table(name = "aoa_status_list")
public class SystemStatusList {
    @Id
    @Column(name = "status_id")
    @KeySql(useGeneratedKeys = true)
    /**
     *状态id
     */
    private Long statusId;
    /**
     * 状态名称
     */
    @Column(name = "status_name")
    @NotBlank(message="状态名称不能为空")
    private String statusName;
    /**
     * // 状态排序值
     */
    @Column(name = "sort_value")
    private Integer statusSortValue;
    /**
     * // 状态模块
     */
    @Column(name = "status_model")
    private String statusModel;
    /**
     * // 状态颜色
     */
    @Column(name = "status_color")
    private String statusColor;
    /**
     * //百分比
     */
    @Column(name = "sort_precent")
    private String statusPrecent;
}
