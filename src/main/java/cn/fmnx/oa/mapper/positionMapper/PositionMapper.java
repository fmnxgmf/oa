package cn.fmnx.oa.mapper.positionMapper;

import cn.fmnx.oa.contoller.position.vo.PositionIdNameVO;
import cn.fmnx.oa.contoller.position.vo.PositionVO;
import cn.fmnx.oa.entity.position.Position;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PositionMapper extends Mapper<Position> {
    @Select("select p.position_id, p.name from aoa_position p")
    List<PositionIdNameVO> findAllPositionIdName();

    PositionVO findOnePositionByID(@Param("positionId")Long positionId);
}
