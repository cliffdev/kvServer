package com.cliffdev.kv.contract;

import java.util.List;


public interface KvService {

    KvResponse<Boolean> exists(String database,String key)  ;

    KvResponse<String> get(String database,String key)  ;

    KvResponse<List<Item>> batchGet(String database,BatchKey batchKey)  ;

    KvResponse<Boolean> put(String database,String key,String value)  ;

    KvResponse<Boolean> delete(String database,String key)  ;

    KvResponse<Boolean> batchDelete(String database,BatchKey batchKey) ;

    KvResponse<Boolean> batchPut(String database, BatchKv batchKv)  ;

}
