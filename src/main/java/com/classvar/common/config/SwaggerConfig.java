package com.classvar.common.config;

import com.classvar.error.ErrorResponse;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
    TypeResolver typeResolver = new TypeResolver();
    List<ResponseMessage> commonResponse = setCommonResponse();
    Set<String> contentType = new HashSet<>();
    contentType.add(MediaType.APPLICATION_JSON_VALUE);

    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .consumes(contentType)
        .produces(contentType)
        .globalResponseMessage(RequestMethod.GET, commonResponse)
        .globalResponseMessage(RequestMethod.POST, commonResponse)
        .globalResponseMessage(RequestMethod.PUT, commonResponse)
        .globalResponseMessage(RequestMethod.DELETE, commonResponse)
        .additionalModels(typeResolver.resolve(ErrorResponse.class)) // ModelRef에서 사용할 모델을 추가한다.
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.classvar"))
        .paths(PathSelectors.any())
        .build()
        .protocols(newHashSet("https"));
  }

  private List<ResponseMessage> setCommonResponse() {
    ModelRef errorModel = new ModelRef("ErrorResponse");

    List<ResponseMessage> responses = new ArrayList<>();

    responses.add(new ResponseMessageBuilder().code(200).message("OK").build());
    responses.add(
        new ResponseMessageBuilder()
            .code(400)
            .message("BAD REQUEST")
            .responseModel(errorModel)
            .build());
    responses.add(
        new ResponseMessageBuilder()
            .code(401)
            .message("UNAUTHORIZED")
            .responseModel(errorModel)
            .build());
    responses.add(
        new ResponseMessageBuilder()
            .code(500)
            .message("INTERNAL SERVER ERROR")
            .responseModel(errorModel)
            .build());
    return responses;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("ClassVAR APIs")
        .description("ClassVAR API 목록입니다.")
        .version("1.0")
        .build();
  }
}
