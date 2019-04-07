package com.cliffdev.kv.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

@Configuration
public class RmiConfig {

    @Value("${rmi.port:2190}")
    private int rmiPort;

    @Value("${rmi.host:localhost}")
    private String rmiHost;

    @Bean
    public RmiRegistryFactoryBean initRmiRegistryFactoryBean() {
        RmiRegistryFactoryBean registry = new RmiRegistryFactoryBean();
        registry.setPort(rmiPort);
        return registry;
    }

}
