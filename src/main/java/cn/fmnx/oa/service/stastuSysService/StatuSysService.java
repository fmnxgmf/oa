package cn.fmnx.oa.service.stastuSysService;

import cn.fmnx.oa.contoller.menu.dto.AddStatusDTO;
import cn.fmnx.oa.contoller.menu.vo.StatusListVO;

import java.util.List;

/**
 * @ClassName StatuSysService
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/12
 * @Version V1.0
 **/
public interface StatuSysService {
    boolean addStatusList(AddStatusDTO addStatusDTO);

    boolean deleteStatusList(Long statusId);

    boolean updateStatusList(AddStatusDTO addStatusDTO);

    StatusListVO findStatusById(Long statusId);

    List<StatusListVO> findAllStatusList();

    List<StatusListVO> findStatusByNameOrModel(String nameOrModel);
}
