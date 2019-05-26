package com.cliffdev.kv.server.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class KvManager {

    @Autowired
    private ApplicationContext applicationContext;

    public   KvStore  getKvStore(String database){
        return applicationContext.getBean("kvStore_"+database,KvStore.class);
    }


}
