package cn.fmnx.oa.entity.role;

import cn.fmnx.oa.entity.menu.Menu;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName Rolepowerlist中间权限表
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/5
 * @Version V1.0
 **/
@Data
@Table(name = "aoa_role_power_list")
public class Rolepowerlist {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "pk_id")
    private Integer pkId;
    @Column(name = "role_id")
    private Integer roleId;


    @Column(name = "menu_id")
    private Menu menuId;


    @Column(name="is_show")
    private Boolean check=false;
}
