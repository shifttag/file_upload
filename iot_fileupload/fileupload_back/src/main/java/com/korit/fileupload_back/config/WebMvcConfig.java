package com.korit.fileupload_back.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Value("${root.path}")
    private String rootPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/image/**")            // http://localhost:8080/image/xxxxxxxx
                .addResourceLocations("file:///" + rootPath)    // file:///C:/Users/ehdgu/OneDrive/바탕 화면/국비 수업/korea/upload/profile
                .resourceChain(true)                        //  캐시, 불필요한 정보 입력 등을 사용 하겠냐 라는 코드 (addResolver 사용하려면 true)
                .addResolver(new PathResourceResolver() {   // 한글 인코딩 안깨지게 하려고 오버라이딩 시켜서 사용
                    @Override       // 파일명에 한글이 있을경우를 대비하여 설정
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        resourcePath = URLDecoder.decode(resourcePath, StandardCharsets.UTF_8);
                        return super.getResource(resourcePath, location);
                    }
                });
    }

}
