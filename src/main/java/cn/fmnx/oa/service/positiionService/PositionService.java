package cn.fmnx.oa.service.positiionService;

import cn.fmnx.oa.contoller.position.dto.PositionDTO;
import cn.fmnx.oa.contoller.position.vo.PositionIdNameVO;
import cn.fmnx.oa.contoller.position.vo.PositionVO;

import java.util.List;

public interface PositionService {
    List<PositionVO> findAllPosition();

    PositionVO findOnePositionByID(Long positionId);

    boolean addPositon(PositionDTO positionDTO);

    boolean updatePosition(PositionDTO positionDTO);
    List<PositionIdNameVO> findAllPositionIdName();
}
