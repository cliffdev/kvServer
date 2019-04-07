package com.cliffdev.kv.server;

import com.cliffdev.kv.contract.BatchKey;
import com.cliffdev.kv.contract.BatchKv;
import com.cliffdev.kv.contract.Item;
import org.rocksdb.RocksDB;
import org.rocksdb.WriteBatch;
import org.rocksdb.WriteOptions;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RocksdbStore implements KvStore {

    Charset defaultCharset = Charset.forName("utf-8");

    private RocksDB rocksDB;

    public RocksdbStore(RocksDB rocksDB){
        this.rocksDB = rocksDB;
    }

    @Override
    public String get(String key) throws Exception {
        return valueToString(rocksDB.get(key.getBytes(defaultCharset)));
    }
    private String valueToString(byte [] b){
        if(b != null){
            return new String(b,defaultCharset);
        }
        return null;
    }

    @Override
    public List<Item> batchGet(BatchKey batchKey) throws Exception {
        List<Item> list = new ArrayList<>();
        for(String key:batchKey.getKeys()){
            String value = get(key);
            if(value != null){
                Item item = new Item();
                item.setKey(key);
                item.setValue(value);
                list.add(item);
            }
        }
        return list;
    }

    @Override
    public boolean put(String key, String value) throws Exception {
        rocksDB.put(key.getBytes(defaultCharset),value.getBytes(defaultCharset));
        return true;
    }

    @Override
    public boolean delete(String key) throws Exception{
        rocksDB.delete(key.getBytes(defaultCharset));
        return true;
    }

    @Override
    public boolean batchPut(BatchKv batchKv) throws Exception{
        WriteOptions writeOptions = new WriteOptions();
        writeOptions.setSync(false);
        WriteBatch writeBatch = new WriteBatch();
        for(Item item:batchKv.getItems()) {
            if(item.getKey() !=null && item.getValue() != null) {
                writeBatch.put(item.getKey().getBytes(defaultCharset), item.getValue().getBytes(defaultCharset));
            }
        }
        rocksDB.write(writeOptions,writeBatch);
        return true;
    }

    @Override
    public boolean batchDelete(BatchKey batchKey) throws Exception {
        WriteOptions writeOptions = new WriteOptions();
        writeOptions.setSync(false);
        WriteBatch writeBatch = new WriteBatch();
        for(String item:batchKey.getKeys()) {
            writeBatch.delete(item.getBytes(defaultCharset));
        }
        rocksDB.write(writeOptions,writeBatch);
        return true;
    }
}
