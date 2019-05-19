package com.cliffdev.kv.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@SpringBootApplication
@EnableScheduling
public class KvServerApp extends AbstractAnnotationConfigDispatcherServletInitializer{

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(KvServerApp.class, args);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/","*.service"};
    }
}
