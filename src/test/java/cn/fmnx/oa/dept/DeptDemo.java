package cn.fmnx.oa.dept;

import cn.fmnx.oa.common.utils.CopyListutils;
import cn.fmnx.oa.contoller.dept.vo.DeptVO;
import cn.fmnx.oa.entity.dept.Dept;
import cn.fmnx.oa.mapper.deptMapper.DeptMapper;
import cn.fmnx.oa.service.deptService.impl.DeptServiceImpl;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.util.ArrayList;
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


    @Test
    public void copyList(){
        List<DeptVO> list1 = new ArrayList();
        List<Dept> list2 = new ArrayList();
        Dept dept = new Dept();
        Dept dept2 = new Dept();
        dept.setDeptAddr("copy");
        dept.setDeptId(2L);
        dept2.setDeptAddr("list");
        dept2.setDeptId(2L);
        list2.add(dept);
        list2.add(dept2);
        System.out.println("复制前");
        list1.forEach(System.out::println);
        CopyListutils.copyListBeanUtils(list2,list1,DeptVO.class);
        System.out.println("复制后");
        list1.forEach(System.out::println);
    }
    @Test
    public void copyListJson(){
        List<DeptVO> list1 = new ArrayList();
        List<Dept> list2 = new ArrayList();
        Dept dept = new Dept();
        Dept dept2 = new Dept();
        dept.setDeptAddr("copy");
        dept.setDeptId(1L);
        dept2.setDeptAddr("list");
        dept2.setDeptId(2L);
        list2.add(dept);
        list2.add(dept2);
        System.out.println("复制前");
        System.out.println("*******************************");
        list1.forEach(System.out::println);
        list1 = CopyListutils.copyListJson(list2, list1, DeptVO.class);
        System.out.println("复制后");
        list1.forEach(System.out::println);
    }
    @Test
    public void findAllDeptIdName(){
        deptMapper.findAllDeptIdAndName().forEach(System.out::println);
    }
}
