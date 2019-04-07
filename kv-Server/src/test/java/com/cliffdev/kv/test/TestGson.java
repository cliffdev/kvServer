package com.cliffdev.kv.test;

import com.cliffdev.kv.contract.BatchKv;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TestGson {

    public static void main(String [] args){
        Gson gson = new Gson();
        List<String> list  = new ArrayList<>();
        list.add("test1");
        list.add("test");

        System.out.println(gson.toJson(list));



    }
}
