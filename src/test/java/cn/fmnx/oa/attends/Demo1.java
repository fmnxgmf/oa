package cn.fmnx.oa.attends;

import cn.fmnx.oa.service.attendsService.impl.AttendsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName Demo1
 * @Description: TODO
 * @Author gmf
 * @Date 2020/3/1
 * @Version V1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo1 {
    @Autowired
    private AttendsServiceImpl attendsService;
    @Test
    public void demo(){
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(date));
//        //月份中星期
//        SimpleDateFormat sdf1 = new SimpleDateFormat("F");
//        System.out.println(sdf1.format(date));
//        //星期中天数
//        SimpleDateFormat sdf2 = new SimpleDateFormat("E");
//        System.out.println(sdf2.format(date));
//        SimpleDateFormat sdf3 = new SimpleDateFormat("EEEE");
//        System.out.println(sdf3.format(date));

        String end = "17:00:00";
        String star = "09:00:00";
        int i = end.compareTo(star);
        System.out.println("i = " + i);
        int i1 = star.compareTo(end);
        System.out.println("i1 = " + i1);
    }
    @Test
    public void switchDemo(){
        SimpleDateFormat sdf2 = new SimpleDateFormat("E");
        String format = sdf2.format(new Date());
        switch (format){
            case "星期一":
            case "星期二":
            case "星期三":
            case "星期四":
            case "星期五":
                System.out.println("上班");
                break;
            case "星期六":
            case "星期日":
                System.out.println("双休");
                break;
        }
    }
    @Test
    public void findsignInCount(){
        Long userId = 1L;
        String ymdDate = "2017-10-09";
        Integer signInCount = attendsService.findSignInCount(userId, ymdDate);
        System.out.println("signInCount = " + signInCount);

    }
}
