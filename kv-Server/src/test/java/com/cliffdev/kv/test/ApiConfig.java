package com.cliffdev.kv.test;


import com.cliffdev.kv.contract.KvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.Properties;

@Configuration
public class ApiConfig {

    @Value("${rmi.kvservice.url}")
    private String rmiKvServiceUrl;

    @Value("${hessian.kvservice.url}")
    private String hessianKvServiceurl;

    @Value("${hessian.kvservice.url}")
    private String hessianKvServiceUrl;

    @Bean(name = "rmiKvService")
    public RmiProxyFactoryBean initRmiKvServiceProxyFactoryBean() {
        RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
        factory.setServiceUrl(rmiKvServiceUrl);
        factory.setServiceInterface(KvService.class);
        factory.setLookupStubOnStartup(false);
        factory.setRefreshStubOnConnectFailure(true);
        factory.afterPropertiesSet();
        return factory;
    }
    @Bean(name = "hessianKvService")
    public HessianProxyFactoryBean initHessianKvServiceProxyFactoryBean() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl(hessianKvServiceUrl);
        factory.setServiceInterface(KvService.class);
        factory.afterPropertiesSet();
        return factory;
    }

}
