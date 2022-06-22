package com.kololantoo.esdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : Liz
 * @date : 2022/2/9
 * @description : Swagger配置
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(createApiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kololantoo.esdemo"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo createApiInfo() {
        return new ApiInfoBuilder()
                .description("es-demo")
                .title("es-demo 接口文档")
                .license("Apache2.0")
                .licenseUrl("http://www,apache.org/licenses/LICENSE-2.0")
                .build();
    }
}

