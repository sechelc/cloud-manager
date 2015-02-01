package com.sechelc.cloud.manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

import java.util.List;

/**
 * Created by sechelc on 01.02.2015.
 */
@Configuration
public class RestConfiguration extends RepositoryRestMvcConfiguration {

    @Override
    public List<HttpMessageConverter<?>> defaultMessageConverters() {
        List<HttpMessageConverter<?>> httpMessageConverters = super.defaultMessageConverters();
        httpMessageConverters.add(new ProtobufHttpMessageConverter());
        return httpMessageConverters;
    }
}
