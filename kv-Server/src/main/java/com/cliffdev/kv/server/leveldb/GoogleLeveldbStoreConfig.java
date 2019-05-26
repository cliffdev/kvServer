package com.cliffdev.kv.server.leveldb;

import com.cliffdev.kv.server.core.KvStore;
import org.iq80.leveldb.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name="kv.store.leveldb", havingValue="true")
public class GoogleLeveldbStoreConfig {

    @Autowired
    private DB db;

    @Bean("kvStore_master")
    public KvStore initLevelDbMaster()   {
        KvStore kvStore = new GoogleLeveldbStore(db);
        return kvStore;
    }

    @Bean("kvStore_second")
    public   KvStore initLevelDbSecond()   {
        KvStore kvStore = new GoogleLeveldbStore(db);
        return kvStore;
    }

    @Bean("kvStore_third")
    public   KvStore initLevelDbThird()   {
        KvStore kvStore = new GoogleLeveldbStore(db);
        return kvStore;
    }

}
