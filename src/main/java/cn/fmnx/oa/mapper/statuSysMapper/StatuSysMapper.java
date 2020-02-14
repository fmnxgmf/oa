package cn.fmnx.oa.mapper.statuSysMapper;

import cn.fmnx.oa.contoller.menu.vo.StatusListVO;
import cn.fmnx.oa.entity.menu.SystemStatusList;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName StatuSysMapper
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/12
 * @Version V1.0
 **/
public interface StatuSysMapper  extends Mapper<SystemStatusList> {

    List<StatusListVO> findAllStatusList();

    List<StatusListVO> findStatusByNameOrModel(@Param("nameOrModel") String nameOrModel);
}
