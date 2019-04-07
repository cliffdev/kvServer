package com.cliffdev.kv.contract;

import java.util.List;

public interface KvService {

    KvResponse<Boolean> exists(String database,String key) throws Exception;

    KvResponse<String> get(String database,String key) throws Exception;

    KvResponse<List<Item>> batchGet(String database,BatchKey batchKey) throws Exception;

    KvResponse<Boolean> put(String database,String key,String value) throws Exception;

    KvResponse<Boolean> delete(String database,String key) throws Exception;

    KvResponse<Boolean> batchDelete(String database,BatchKey batchKey) throws Exception;

    KvResponse<Boolean> batchPut(String database, BatchKv batchKv) throws Exception;

}
