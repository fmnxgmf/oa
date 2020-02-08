package cn.fmnx.oa.common.swagger;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

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
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization")
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)//header中的ticket参数非必填，传空也可以
                .build();
        List<Parameter> aParameters = Lists.newArrayList();
        aParameters.add(aParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("cn.fmnx.oa.contoller"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(aParameters)
                .apiInfo(new ApiInfoBuilder()
                        .title("swagger后端接口文档")
                        .description("OA自动化项目后端接口文档")
                        .version("9.0")
                                // 作者信息
                                .contact(new Contact("fmnx","blog.csdn.net","894697547@qq.com"))
                                .license("The Apache License")
                                .licenseUrl("http://www.baidu.com")
                                .build());
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://localhost:8010/swagger-ui.html
     * @return
     */
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Spring Boot中使用Swagger2构建RESTful APIs")
//                .description("更多请关注http://www.baidu.com")
//                .termsOfServiceUrl("http://www.baidu.com")
//                .version("1.0")
//                .build();
//    }

}
