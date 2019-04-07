package com.cliffdev.kv.test;

import com.cliffdev.kv.contract.KvResponse;
import com.cliffdev.kv.contract.KvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TestApp {

    @Autowired
    @Qualifier("rmiKvService")
    private KvService kvService;

    @Autowired
    @Qualifier("hessianKvService")
    private KvService hessianKvService;


    @RequestMapping("/get")
    public KvResponse<String> get(String database, String key) throws Exception {
        return  kvService.get(database,key);
    }

    @RequestMapping("/hessian/get")
    public KvResponse<String> hessianGet(String database, String key) throws Exception {
        return  hessianKvService.get(database,key);
    }

    @RequestMapping("/put")
    public KvResponse<Boolean> put(String database,String key,String value) throws Exception {
        return  kvService.put(database,key,value);
    }

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(TestApp.class, args);
    }


}
