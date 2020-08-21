package com.wlliu.blog.base.config.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger3Config {

    public static final String VERSION = "1.0.0";

    @Bean
    public Docket webRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wlliu.blog.service.*.controller.*.java"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("API文档")
                .description("blog后台api接口定义")
                .contact(new Contact("blog", "#", "wlliu@qq.com"))
                .version(VERSION)
                .build();
    }

}