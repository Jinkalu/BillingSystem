//package com.billingsys.main.config;
//
//import lombok.Value;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@EnableSwagger2
//@Configuration
//
//public class BillConfig {
//    @Value("${app.version}")
//    private String version;
//
//
//    private String title;
//
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                .title(title)
//                .version(version)
//                .build();
//    }
//}
