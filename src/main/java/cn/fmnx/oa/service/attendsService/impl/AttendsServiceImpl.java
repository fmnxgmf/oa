package cn.fmnx.oa.service.attendsService.impl;

import cn.fmnx.oa.mapper.attendsMapper.AttendsMapper;
import cn.fmnx.oa.service.attendsService.AttendsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName AttendsServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/3/1
 * @Version V1.0
 **/
@Service
@Transactional
public class AttendsServiceImpl implements AttendsService {
    @Resource
    private AttendsMapper attendsMapper;
    @Override
    public Integer findSignInCount(Long attendsUserId, String ymdDate) {

        return attendsMapper.findSignInCount(attendsUserId,ymdDate);
    }
}
