package com.cliffdev.kv.contract;

import java.io.Serializable;
import java.util.List;

public class BatchKey implements Serializable {

    private List<String> keys;

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }
}
