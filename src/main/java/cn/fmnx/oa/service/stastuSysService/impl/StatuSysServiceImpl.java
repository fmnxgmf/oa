package cn.fmnx.oa.service.stastuSysService.impl;

import ch.qos.logback.core.status.StatusManager;
import cn.fmnx.oa.contoller.menu.dto.AddStatusDTO;
import cn.fmnx.oa.contoller.menu.vo.StatusListVO;
import cn.fmnx.oa.entity.menu.SystemStatusList;
import cn.fmnx.oa.mapper.statuSysMapper.StatuSysMapper;
import cn.fmnx.oa.service.stastuSysService.StatuSysService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName StatuSysServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/12
 * @Version V1.0
 **/
@Service
@Transactional
public class StatuSysServiceImpl implements StatuSysService {
    @Resource
    private StatuSysMapper statuSysMapper;
    @Override
    public boolean addStatusList(AddStatusDTO addStatusDTO) {
        SystemStatusList statusList = new SystemStatusList();
        statusList.setStatusColor(addStatusDTO.getStatusColor());
        statusList.setStatusModel(addStatusDTO.getStatusModel());
        statusList.setStatusName(addStatusDTO.getStatusName());
        statusList.setStatusSortValue(addStatusDTO.getStatusSortValue());
        int insert = statuSysMapper.insert(statusList);
        if (insert > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStatusList(Long statusId) {
        int i = statuSysMapper.deleteByPrimaryKey(statusId);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStatusList(AddStatusDTO addStatusDTO) {
        SystemStatusList statusList = new SystemStatusList();
        statusList.setStatusId(addStatusDTO.getStatusId());
        statusList.setStatusColor(addStatusDTO.getStatusColor());
        statusList.setStatusModel(addStatusDTO.getStatusModel());
        statusList.setStatusName(addStatusDTO.getStatusName());
        statusList.setStatusSortValue(addStatusDTO.getStatusSortValue());
        int i = statuSysMapper.updateByPrimaryKey(statusList);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    public StatusListVO findStatusById(Long statusId) {
        StatusListVO statusListVO = new StatusListVO();

        SystemStatusList statusList = statuSysMapper.selectByPrimaryKey(statusId);
        BeanUtils.copyProperties(statusList,statusListVO);

        return statusListVO;
    }

    @Override
    public List<StatusListVO> findAllStatusList() {
        return statuSysMapper.findAllStatusList();

    }

    @Override
    public List<StatusListVO> findStatusByNameOrModel(String nameOrModel) {

        return statuSysMapper.findStatusByNameOrModel(nameOrModel);
    }
}
