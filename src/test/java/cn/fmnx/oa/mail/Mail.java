package cn.fmnx.oa.mail;

import cn.fmnx.oa.mapper.mailMapper.MailNumberMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName Mail
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/22
 * @Version V1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class Mail {
    @Resource
    private MailNumberMapper mailNumberMapper;
    @Test
    public void findMailByUserId(){
        mailNumberMapper.findMailByUserId(1L).forEach(System.out::println);
    }
    @Test
    public void findMailByLike(){
        mailNumberMapper.findMailByLike(1L,"事").forEach(System.out::println);
    }
}
