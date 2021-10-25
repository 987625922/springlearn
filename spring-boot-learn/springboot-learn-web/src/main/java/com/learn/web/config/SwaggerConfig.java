package com.learn.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger2API文档的配置
 * <p>
 * 生成指定包下面的类的API文档
 * 生成有指定注解的类的API文档
 * 生成有指定注解的方法的API文档
 *
 * swagger常用注解
 * swagger2	                                      swagger3	                             注解位置
 * @Api(tags=“接口描述”)                           @Api(tags=“接口描述”)	                 controller类上
 * @ApiOperation(value = “接口方法描述”)	          @Operation(summary = “接口方法描述”)	 controller 方法上
 * @ApiModelProperty(value = “字段描述”)	          @ApiModelProperty(value = “字段描述”)	 JavaBean的字段上
 * @ApiModel(“实体类的描述”)                      @ApiModel(“实体类的描述”)	             JavaBean上
 * @EnableSwagger2                               @EnableOpenApi	                         配置类上
 * @ApiImplicitParams                            @ApiImplicitParams	                     controller的方法参数中
 * @ApiImplicitParam                             @ApiImplicitParam	                     @ApiImplicitParams 下的的子参数
 * @ApiParam(name = “参数描述”)	                 @Parameter(description = “参数描述”)	 controller 方法的参数中
 *
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                //为当前包下controller生成API文档
//                .apis(RequestHandlerSelectors.basePackage("com.macro.mall.tiny.controller"))
                //为有@Api注解的Controller生成API文档
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //为有@ApiOperation注解的方法生成API文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SwaggerUI演示")
                .description("ww")
                .contact(new Contact("接口文档", "https://www.baidu.com", "bd@baidu.com"))
                .version("1.0")
                .build();
    }

}
