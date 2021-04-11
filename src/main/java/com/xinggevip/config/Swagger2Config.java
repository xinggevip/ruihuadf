package com.xinggevip.config;

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
public class Swagger2Config {
    /**
     * 在IOC容器里面创建可以对象可以监控Controller里面的是否有Swagger相关的注解
     * 如果，swagger会生成相关的文档
     * @return
     */
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 根据用的注解扫描api
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 根据包路径扫描api
                .apis(RequestHandlerSelectors.basePackage("com.xinggevip.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                // 文档标题
                .title("API在线管理")
                // 文档描述
                .description("客户管理系统")
                // 联系方式
                .contact(new Contact("星哥","http://qiangssvip.com","1511844263@qq.com"))
                // 定义版本号
                .version("1.0")
                // 执照
                .license("高公子")
                .build();

    }
}
