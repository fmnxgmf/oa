package cn.fmnx.oa.mail;

import cn.fmnx.oa.mapper.mailMapper.InmaillistMapper;
import cn.fmnx.oa.mapper.mailMapper.MailNumberMapper;
import cn.fmnx.oa.mapper.mailMapper.MailreciverMapper;
import cn.fmnx.oa.service.mailService.impl.MailNumServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private InmaillistMapper inmaillistMapper;
    @Resource
    private MailNumServiceImpl mailNumService;
    @Resource
    private MailreciverMapper mailreciverMapper;
    @Test
    public void findMailByUserId(){
        mailNumberMapper.findMailByUserId(1L).forEach(System.out::println);
    }
    @Test
    public void findMailByLike(){
        mailNumberMapper.findMailByLike(1L,"事").forEach(System.out::println);
    }
    @Test
    public void findInmailType(){
        inmaillistMapper.findInMailListType().forEach(System.out::println);
    }
    @Test
    public void findMailStatus(){
        inmaillistMapper.findMailStatus().forEach(System.out::println);
    }
    @Test
    public void findMailStatu(){
        mailNumService.findMailNum(1L).forEach(System.out::println);
    }
    @Test
    public void findMailBook(){
        mailNumService.findMailUsers(1L).forEach(System.out::println);
    }
    @Test
    public void showMailInBox(){
        mailreciverMapper.showMailInBox(2L).forEach(System.out::println);
    }
    @Test
    public void arrayDemo(){
        String[] s = {"2","3"};
        inmaillistMapper.findUserNameById(s).forEach(System.out::println);
    }
    @Test
    public void deleteMailInBox(){
        List<Long> list = new ArrayList<>();
        list.add(2L);list.add(3L);list.add(4L);
        Integer integer = mailNumberMapper.deleteMailInBox(list);
        System.out.println("integer = " + integer);
    }
    @Test
    public void setStar(){
        Map map1 = new HashMap();
        map1.put(2,1);
        Map map3 = new HashMap();
        map3.put(3,1);
        Map map2 = new HashMap();
        map2.put(4,1);
        List list = new ArrayList();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        Integer integer = mailNumberMapper.setMailInBoxStar(list);
        System.out.println("integer = " + integer);

    }
}
