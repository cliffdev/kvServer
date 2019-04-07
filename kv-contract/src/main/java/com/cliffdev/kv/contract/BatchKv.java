package com.cliffdev.kv.contract;


import java.io.Serializable;
import java.util.List;

public class BatchKv  implements Serializable{

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
