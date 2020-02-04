package cn.fmnx.oa.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/20 17:28
 * @version:
 * @modified By:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket creatRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("swagger后端接口文档")
                        .description("OA自动化项目后端接口文档")
                        .version("9.0")
                                // 作者信息
                                .contact(new Contact("fmnx","blog.csdn.net","894697547@qq.com"))
                                .license("The Apache License")
                                .licenseUrl("http://www.baidu.com")
                                .build());
    }
}
