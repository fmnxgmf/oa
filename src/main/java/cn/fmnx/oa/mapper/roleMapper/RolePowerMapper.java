package cn.fmnx.oa.mapper.roleMapper;

import cn.fmnx.oa.contoller.role.dto.RolePowerDTO;
import cn.fmnx.oa.entity.role.Rolepowerlist;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName RolePowerMapper
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/15
 * @Version V1.0
 **/
public interface RolePowerMapper extends Mapper<Rolepowerlist> {
    Integer insertRolePower(@Param("rolePowerDTO") RolePowerDTO rolePowerDTO);
    Integer deleteRolePower(@Param("rolePowerDTO") RolePowerDTO rolePowerDTO);
}
