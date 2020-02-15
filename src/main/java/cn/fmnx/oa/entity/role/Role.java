package cn.fmnx.oa.entity.role;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName Role
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/5
 * @Version V1.0
 **/
@Data
@Table(name = "aoa_role_")
public class Role {
    /**
     * //角色id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name="role_id")
    private Long roleId;
    /**
     * //角色名
     */
    @Column(name="role_name")
    private String roleName;
    /**
     * //角色权限值
     */
    @Column(name="role_value")
    private Integer  roleValue;
}
