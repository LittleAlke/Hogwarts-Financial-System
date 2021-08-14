package com.alke.hfs.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

/**
 * :
 * Alke
 * 2021-08-13 15:39
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket adminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }

    public ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("霍格沃兹金融后台管理系统API文档")
                .description("本文档描述了霍格沃兹金融后台管理系统的各个模块接口的调用方式")
                .contact(new Contact("Alke","https://github.com/LittleAlke","plm5649@hotmail.com"))
                .build();
    }

    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }

    public ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("霍格沃兹金融网站API文档")
                .description("本文档描述了霍格沃兹金融网站的各个模块接口的调用方式")
                .contact(new Contact("Alke","https://github.com/LittleAlke","plm5649@hotmail.com"))
                .build();
    }
}
