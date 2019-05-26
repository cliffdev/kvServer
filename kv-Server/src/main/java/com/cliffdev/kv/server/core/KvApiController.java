package com.cliffdev.kv.server.core;

import com.cliffdev.kv.contract.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
