package cn.fmnx.oa.position;

import cn.fmnx.oa.contoller.position.vo.PositionVO;
import cn.fmnx.oa.mapper.positionMapper.PositionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName PositionDemo
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/14
 * @Version V1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class PositionDemo {
    @Resource
    private PositionMapper positionMapper;
    @Test
    public void findAllPositionIdName(){
        positionMapper.findAllPositionIdName().forEach(System.out::println);
    }
    @Test
    public void findOnePositinById(){
        PositionVO onePositionByID = positionMapper.findOnePositionByID(1L);
        System.out.println("onePositionByID = " + onePositionByID);
    }
}
