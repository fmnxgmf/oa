package cn.fmnx.oa.service.positiionService.impl;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.common.utils.CopyListutils;
import cn.fmnx.oa.contoller.position.dto.PositionDTO;
import cn.fmnx.oa.contoller.position.vo.PositionIdNameVO;
import cn.fmnx.oa.contoller.position.vo.PositionVO;
import cn.fmnx.oa.entity.position.Position;
import cn.fmnx.oa.mapper.positionMapper.PositionMapper;
import cn.fmnx.oa.service.positiionService.PositionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PositionServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/13
 * @Version V1.0
 **/
@Service
@Transactional
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionMapper positionMapper;
    @Override
    public PageResult<PositionVO> findAllPosition(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<Position> positions = positionMapper.selectAll();
        List<PositionVO> positionVOS = new ArrayList<>();
        CopyListutils.copyListBeanUtils(positions,positionVOS,PositionVO.class);
        if (!CollectionUtils.isEmpty(positionVOS)){
            PageResult<PositionVO> pageResult = new PageResult<>(new PageInfo<PositionVO>(positionVOS));
            return pageResult;
        }else {
            return null;
        }
    }

    @Override
    public PositionVO findOnePositionByID(Long positionId) {
        PositionVO positionVO =  positionMapper.findOnePositionByID(positionId);
        if (!StringUtils.isEmpty(positionVO)){
            return positionVO;
        }
        return null;
    }

    @Override
    public boolean addPositon(PositionDTO positionDTO) {
        Position position = new Position();
        BeanUtils.copyProperties(positionDTO,position);
        if (!StringUtils.isEmpty(position)){
            int insert = positionMapper.insert(position);
            if (insert > 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatePosition(PositionDTO positionDTO) {
        Position position = new Position();
        BeanUtils.copyProperties(positionDTO,position);
        if (! StringUtils.isEmpty(position)){
            int i = positionMapper.updateByPrimaryKey(position);
            if (i > 0){
                return true;
            }
        }
        return false;
    }
    @Override
    public List<PositionIdNameVO> findAllPositionIdName() {
       List<PositionIdNameVO> positionIdNameVOS = positionMapper.findAllPositionIdName();
        return positionIdNameVOS;
    }
}
