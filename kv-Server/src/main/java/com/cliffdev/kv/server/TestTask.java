package com.cliffdev.kv.server;

import com.cliffdev.kv.contract.BatchKv;
import com.cliffdev.kv.contract.Item;
import com.cliffdev.kv.contract.KvService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class TestTask {

    @Autowired
    @Qualifier("kvService")
    private KvService kvService;

    @Value("${test.task.count:2000}")
    private int count;

    @Value("${test.task.database:master}")
    private String database;

    @Value("${test.flag:true}")
    private boolean test;

    private AtomicInteger seq = new AtomicInteger();

    Gson gson = new Gson();

    @Scheduled(cron="${test.task.corn:*/1 * * * * ?}")
    public void testData( )  {
        if(!test){
            return ;
        }
        try{
            BatchKv batchKv = new BatchKv();
            List<Item> list = new ArrayList<>(count);
            for(int i=0;i<count;i++){
                Item item = new Item();
                Map<String,String> map = getTestMessage();
                String json = gson.toJson(getTestMessage());
                item.setValue(json);
                item.setKey(map.get("id"));
                list.add(item);
            }
            batchKv.setItems(list);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            kvService.batchPut(database,batchKv);
            stopWatch.stop();
            log.info("test seq:{},time:{}",seq.addAndGet(1),stopWatch.getTotalTimeMillis());

        }catch (Exception ex){
            log.error("同步回执处理异常",ex);
        }
    }

    private Map<String,String> getTestMessage() {
        Map<String, String> map = new HashMap<>();
        map.put("id",UUID.randomUUID().toString());
        for (int i = 0; i < 10; i++){
            map.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        map.put("content","这是测试的内容，这是测试的内容，这是测试的内容" +
                "，这是测试的内容，这是测试的内容，这是测试的内容，这是测试的内容"+UUID.randomUUID().toString());

        return map;
    }

}
