package cn.fmnx.oa;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("cn.fmnx.oa.mapper")
@Slf4j
public class OaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaApplication.class, args);
        log.info("spirngboot启动成功");
    }

}
