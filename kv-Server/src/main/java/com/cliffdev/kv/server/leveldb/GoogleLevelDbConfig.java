package com.cliffdev.kv.server.leveldb;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
@ConditionalOnProperty(name="kv.store.leveldb", havingValue="true")
public class GoogleLevelDbConfig {
	
	@Value("${google.levledb.dir}")
	private String leveldbDir;
	@Value("${google.levledb.writeBufferSize:33554432}")
	private int writeBufferSize;

	//1000
	@Value("${google.levledb.maxOpenFiles:1000}")
	private int maxOpenFiles;

	//1G
	@Value("${google.levledb.cacheSize:1073741824}")
	private long cacheSize;

	@Value("${google.levledb.blockSize:4096}")
	private int blockSize;

	@Value("${google.levledb.blockRestartInterval:16}")
	private int blockRestartInterval;

	@Bean
	public DB initGoogleLevelDb() throws IOException {
		boolean cleanup = false;
        DBFactory factory = Iq80DBFactory.factory;
        File dir = new File(leveldbDir);
        //如果数据不需要reload，则每次重启，尝试清理磁盘中path下的旧数据。
        if(cleanup) {
            factory.destroy(dir,null);//清除文件夹内的所有文件。
        }
        Options options = new Options().createIfMissing(true);
        options.writeBufferSize(writeBufferSize);
        options.maxOpenFiles(maxOpenFiles);
		options.cacheSize(cacheSize);
		options.blockSize(blockSize);
		options.blockRestartInterval(blockRestartInterval);

        //重新open新的db
        return factory.open(dir,options);
	}


}
