package com.cliffdev.kv.server.leveldb;

import com.cliffdev.kv.contract.BatchKey;
import com.cliffdev.kv.contract.BatchKv;
import com.cliffdev.kv.contract.Item;
import com.cliffdev.kv.server.core.KvStore;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.WriteBatch;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public  class GoogleLeveldbStore implements KvStore {

    Charset defaultCharset = Charset.forName("utf-8");

    private DB db;

    public GoogleLeveldbStore(DB db){
        this.db = db;
    }

    @Override
    public String get(String key) throws Exception {
        return valueToString(db.get(key.getBytes(defaultCharset)));
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
        db.put(key.getBytes(defaultCharset),value.getBytes(defaultCharset));
        return true;
    }

    @Override
    public boolean delete(String key) throws Exception{
        db.delete(key.getBytes(defaultCharset));
        return true;
    }

    @Override
    public boolean batchPut(BatchKv batchKv) throws Exception{
        try( WriteBatch writeBatch = db.createWriteBatch()) {
            for (Item item : batchKv.getItems()) {
                if (item.getKey() != null && item.getValue() != null) {
                    writeBatch.put(item.getKey().getBytes(defaultCharset), item.getValue().getBytes(defaultCharset));
                }
            }
            db.write(writeBatch);
        }

        return true;
    }

    @Override
    public boolean batchDelete(BatchKey batchKey) throws Exception {
        try( WriteBatch writeBatch = db.createWriteBatch()) {
            for (String item : batchKey.getKeys()) {
                writeBatch.delete(item.getBytes(defaultCharset));
            }
            db.write(writeBatch);
        }
        return true;
    }
}