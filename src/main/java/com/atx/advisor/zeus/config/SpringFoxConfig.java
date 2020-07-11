package com.atx.advisor.zeus.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Slf4j
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        log.info("SpringFoxConfig : Creating Docket Bean for Swagger2 . . .");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        log.info("SpringFoxConfig : Generating ApiInfo for Swagger2 . . .");
        return new ApiInfoBuilder()
                .title("ZEUS REST APIs")
                .description("Atx Advisor Zeus Service Public APIs")
                .build();
    }

    @Bean
    public LinkDiscoverers discoverers() {
        log.info("SpringFoxConfig : Creating LinkDiscoverers Bean for Swagger2 . . .");
        // https://github.com/spring-projects/spring-hateoas/issues/1094
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }
}
