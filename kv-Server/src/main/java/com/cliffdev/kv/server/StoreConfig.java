package com.cliffdev.kv.server;

import org.rocksdb.RocksDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreConfig {

    @Qualifier("master")
    @Autowired
    private RocksDB master;

    @Qualifier("second")
    @Autowired
    private RocksDB second;

    @Qualifier("third")
    @Autowired
    private RocksDB third;

    @Bean("kvStore_master")
    public   KvStore initMasterStoreImpl()   {
        KvStore kvStore = new RocksdbStore(master);
        return kvStore;
    }

    @Bean("kvStore_second")
    public   KvStore initSecondStoreImpl()   {
        KvStore kvStore = new RocksdbStore(second);
        return kvStore;
    }

    @Bean("kvStore_third")
    public   KvStore initThirdStoreImpl()   {
        KvStore kvStore = new RocksdbStore(third);
        return kvStore;
    }
}
