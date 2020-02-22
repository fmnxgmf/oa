package cn.fmnx.oa.entity.position;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName Position
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/5
 * @Version V1.0
 **/
@Data
@Table(name = "aoa_position")
public class Position {

    @Id
    @Column(name = "position_id")
    @KeySql(useGeneratedKeys = true)
    /**
     * //职位id
     */
    private Long positionId;
    /**
     * //职位名称。
     */
    @Column(unique = true)
    private String name;
    /**
     * //职位层级
     */
    private Integer level;
    /**
     * //职位描述
     */
    private String describtion;

    private Long deptid;
}
