package cn.fmnx.oa.role;

import cn.fmnx.oa.mapper.roleMapper.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName RoleDemo
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/14
 * @Version V1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleDemo {
    @Resource
    private RoleMapper roleMapper;
    @Test
    public void findAllRoleIdName(){
        roleMapper.findAllRoleIdName().forEach(System.out::println);
    }
}
