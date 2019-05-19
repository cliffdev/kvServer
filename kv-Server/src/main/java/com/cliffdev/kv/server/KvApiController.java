package com.cliffdev.kv.server;

import com.cliffdev.kv.contract.*;
import lombok.extern.slf4j.Slf4j;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web/api")
@Slf4j
public class KvApiController {

    @Autowired
    @Qualifier("kvService")
    private KvService kvService;

    @Autowired
    @Qualifier("master")
    private RocksDB rocksDB;


    @RequestMapping("/test")
    public KvResponse<String> test() throws Exception {
        long rows = 1;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        RocksIterator iterator = rocksDB.newIterator();
        for (iterator.seekToLast(); iterator.isValid(); iterator.prev()) {
            iterator.status();
            rows ++;
        }
        stopWatch.stop();
        log.info(" rocksbd rows:{},time:{}",rows,stopWatch.getTotalTimeMillis()/1000.00);
        return   new KvResponse<String>();
    }

    @RequestMapping("/exists")
    public KvResponse<Boolean> exists(String database,String key) throws Exception {
        return  kvService.exists(database,key);
    }

    @RequestMapping("/get")
    public KvResponse<String> get(String database,String key) throws Exception {
        return  kvService.get(database,key);
    }
    @RequestMapping("/batchGet")
    public KvResponse<List<Item>> batchGet(String database, @RequestBody BatchKey batchKey) throws Exception {
        return  kvService.batchGet(database,batchKey);
    }

    @RequestMapping("/put")
    public KvResponse<Boolean> put(String database,String key,String value) throws Exception {
        return  kvService.put(database,key,value);
    }
    @RequestMapping
            ("/batchPut")
    public KvResponse<Boolean> batchPut(String database, @RequestBody BatchKv batchKv) throws Exception {
        return  kvService.batchPut(database,batchKv);
    }
    @RequestMapping("/delete")
    public KvResponse<Boolean> delete(String database,String key) throws Exception {
        return  kvService.delete(database,key);
    }
    @RequestMapping("/batchDelete")
    public KvResponse<Boolean> batchDelete(String database,@RequestBody BatchKey batchKey) throws Exception {
        return  kvService.batchDelete(database,batchKey);
    }

}
