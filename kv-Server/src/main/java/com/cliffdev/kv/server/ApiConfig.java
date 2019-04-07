package com.cliffdev.kv.server;


import com.cliffdev.kv.contract.KvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.Properties;

@Configuration
public class ApiConfig {

    @Autowired
    private KvService kvService;

    @Autowired
    private RmiRegistryFactoryBean rmiRegistryFactoryBean;

    @Bean("rmiKvService")
    public RmiServiceExporter initRmiKvServiceExporter() throws Exception{
        RmiServiceExporter exporter=new RmiServiceExporter();
        exporter.setServiceInterface(KvService.class);
        exporter.setServiceName("kvService");
        exporter.setService(kvService);
        exporter.setRegistry(rmiRegistryFactoryBean.getObject());
        return exporter;
    }

    @Bean(name="/hessianKvService.service")
    public HessianServiceExporter initHessianKvServiceServiceExporter() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setServiceInterface(KvService.class);
        exporter.setService(kvService);
        return exporter;
    }
}
