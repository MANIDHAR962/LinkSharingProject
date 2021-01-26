package ttn.bootcamp.linksharing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ttn.bootcamp.linksharing.interceptor.Interceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    Interceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor);
    }
}