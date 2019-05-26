package com.cliffdev.kv.server.core;

import com.cliffdev.kv.contract.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component("kvService")
public class KvServiceImpl implements KvService {

    @Autowired
    private KvManager kvManager;

    @Override
    public KvResponse<Boolean> exists(String database, String key) {
        KvResponse<Boolean> response = new KvResponse<>();
        KvStore kvStore = kvManager.getKvStore(database);
        if (kvStore == null){
            log.warn("database :{} 不存在",database);
            response.setCode(KvResponse.ERROR);
            return response;
        }
        if (key == null){
            response.setCode(KvResponse.ERROR);
            return response;
        }

        try {
            String value =  kvStore.get(key);
            if(value != null) {
                response.setCode(KvResponse.SUCCESS);
                response.setData(true);
            }
            return  response;
        }catch (Exception ex){
            log.error("database:{},get key :{} 异常",database,key,ex);
        }
        response.setCode(KvResponse.ERROR);
        return  response;
    }

    @Override
    public KvResponse<String> get(String database, String key) {
        KvResponse<String> response = new KvResponse<>();
        KvStore kvStore = kvManager.getKvStore(database);
        if (kvStore == null){
            log.warn("database :{} 不存在",database);
            response.setCode(KvResponse.ERROR);
            return response;
        }
        if (key == null){
            response.setCode(KvResponse.ERROR);
            return response;
        }

        try {
            String value =  kvStore.get(key);
            response.setCode(KvResponse.SUCCESS);
            response.setData(value);
            return  response;
        }catch (Exception ex){
            log.error("database:{},get key :{} 异常",database,key,ex);
        }
        response.setCode(KvResponse.ERROR);
        return  response;
    }

    @Override
    public KvResponse<List<Item>> batchGet(String database, BatchKey batchKey) {
        KvResponse<List<Item>> response = new KvResponse<>();
        KvStore kvStore = kvManager.getKvStore(database);
        if (kvStore == null){
            log.warn("database :{} 不存在",database);
            response.setCode(KvResponse.ERROR);
            return response;
        }
        if (batchKey == null){
            response.setCode(KvResponse.ERROR);
            return response;
        }
        try {
            List<Item>  list = kvStore.batchGet(batchKey);
            response.setCode(KvResponse.SUCCESS);
            response.setData(list);
            return  response;
        }catch (Exception ex){
            log.error("database:{},batchGet 异常",database,ex);
        }
        response.setCode(KvResponse.ERROR);
        return  response;
    }

    @Override
    public KvResponse<Boolean> put(String database, String key, String value) {
        KvResponse<Boolean> response = new KvResponse<>();
        KvStore kvStore = kvManager.getKvStore(database);
        if (kvStore == null){
            log.warn("database :{} 不存在",database);
            response.setCode(KvResponse.ERROR);
            return response;
        }
        if (key == null || value ==null){
            response.setCode(KvResponse.ERROR);
            return response;
        }
        try {
            boolean flag =  kvStore.put(key,value);
            response.setCode(KvResponse.SUCCESS);
            response.setData(flag);
            return  response;
        }catch (Exception ex){
            log.error("database:{},put 异常",database,ex);
        }
        response.setCode(KvResponse.ERROR);
        return  response;
    }

    @Override
    public KvResponse<Boolean> delete(String database, String key) {
        KvResponse<Boolean> response = new KvResponse<>();
        KvStore kvStore = kvManager.getKvStore(database);
        if (kvStore == null){
            log.warn("database :{} 不存在",database);
            response.setCode(KvResponse.ERROR);
            return response;
        }
        if (key == null){
            response.setCode(KvResponse.ERROR);
            return response;
        }
        try {
            boolean flag = kvStore.delete(key);
            response.setCode(KvResponse.SUCCESS);
            response.setData(flag);
            return  response;
        }catch (Exception ex){
            log.error("database:{},delete key:{} 异常",database,key,ex);
        }
        response.setCode(KvResponse.ERROR);
        return  response;
    }

    @Override
    public KvResponse<Boolean> batchDelete(String database, BatchKey batchKey) {
        KvResponse<Boolean> response = new KvResponse<>();
        KvStore kvStore = kvManager.getKvStore(database);
        if (kvStore == null){
            log.warn("database :{} 不存在",database);
            response.setCode(KvResponse.ERROR);
            return response;
        }
        if (batchKey == null || batchKey.getKeys() == null || batchKey.getKeys().isEmpty()){
            response.setCode(KvResponse.ERROR);
            return response;
        }
        try {
            boolean flag = kvStore.batchDelete(batchKey);
            response.setCode(KvResponse.SUCCESS);
            response.setData(flag);
            return  response;
        }catch (Exception ex){
            log.error("database:{},delete batchKey:{} 异常",database,batchKey,ex);
        }
        response.setCode(KvResponse.ERROR);
        return response;
    }


    @Override
    public KvResponse<Boolean> batchPut(String database,BatchKv batchKv) {
        KvResponse<Boolean> response = new KvResponse<>();
        KvStore kvStore = kvManager.getKvStore(database);
        if (kvStore == null){
            log.warn("database :{} 不存在",database);
            response.setCode(KvResponse.ERROR);
            return response;
        }
        try {
            boolean flag = kvStore.batchPut(batchKv);
            response.setCode(KvResponse.SUCCESS);
            response.setData(flag);
            return  response;
        }catch (Exception ex){
            log.error("database:{},batchPut 异常",database,ex);
        }
        response.setCode(KvResponse.ERROR);
        return  response;
    }
}
