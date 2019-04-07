package com.cliffdev.kv.server;

import com.cliffdev.kv.contract.BatchKey;
import com.cliffdev.kv.contract.BatchKv;
import com.cliffdev.kv.contract.Item;

import java.util.List;

public interface KvStore {

    String get(String key) throws Exception;

    List<Item> batchGet(BatchKey  batchKey) throws Exception;

    boolean put(String key,String value) throws Exception;

    boolean delete(String key) throws Exception;

    boolean batchPut(BatchKv batchKv) throws Exception;

    boolean batchDelete(BatchKey batchKey) throws Exception;

}
