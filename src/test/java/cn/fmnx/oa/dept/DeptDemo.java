package cn.fmnx.oa.dept;

import cn.fmnx.oa.contoller.dept.vo.DeptVO;
import cn.fmnx.oa.mapper.deptMapper.DeptMapper;
import cn.fmnx.oa.service.deptService.impl.DeptServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DeptDemo
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/12
 * @Version V1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class DeptDemo {
    @Resource
    private DeptMapper deptMapper;
    @Autowired
    private DeptServiceImpl deptService;
    @Test
    public void findAllDept(){
        List<DeptVO> allDept = deptMapper.findAllDept();
        allDept.forEach(System.out::println);
    }
    @Test
    public void findOneDeptById(){
        DeptVO oneDeptById = deptService.findOneDeptById(1L);
        System.out.println("oneDeptById = " + oneDeptById);
    }
}
