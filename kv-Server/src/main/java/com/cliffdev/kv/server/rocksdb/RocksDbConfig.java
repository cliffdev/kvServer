package com.cliffdev.kv.server.rocksdb;

import org.rocksdb.*;
import org.rocksdb.util.SizeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@ConditionalOnProperty(name="kv.store.rocksdb", havingValue="true")
public class RocksDbConfig {

    static {
        RocksDB.loadLibrary();
    }

    @Value("${rocksdb.options.writeBufferSize:8}")
    private long writeBufferSize;

    @Value("${rocksdb.options.maxWriteBufferNumber:3}")
    private int maxWriteBufferNumber;

    @Value("${rocksdb.options.maxBackgroundCompactions:10}")
    private int maxBackgroundCompactions;

    @Value("${rocksdb.options.compressionType:LZ4_COMPRESSION}")
    private String compressionType;

    @Value("${rocksdb.options.master.dir:/data/rocksdb/master}")
    private String masterDir;

    @Value("${rocksdb.options.second.dir:/data/rocksdb/second}")
    private String secondDir;

    @Value("${rocksdb.options.third.dir:/data/rocksdb/third}")
    private String thirdDir;

    private Options getOptions(){
        final Statistics stats = new Statistics();
        final Options options = new Options();
        options.setCreateIfMissing(true)
                .setStatistics(stats)
                .setWriteBufferSize( writeBufferSize * SizeUnit.KB)
                .setMaxWriteBufferNumber(maxWriteBufferNumber)
                .setMaxBackgroundCompactions(maxBackgroundCompactions)
                .setCompressionType(CompressionType.LZ4_COMPRESSION)
                .setCompactionStyle(CompactionStyle.UNIVERSAL);
        return options;
    }

    @Bean("master")
    public   RocksDB intMasterRocksdb() throws RocksDBException{
        Options options = getOptions();
        createDir(masterDir);
        final RocksDB db = RocksDB.open(options, masterDir);
        return db;
    }

    @Bean("second")
    public   RocksDB intSecondRocksdb() throws RocksDBException{
        Options options = getOptions();
        createDir(secondDir);
        final RocksDB db = RocksDB.open(options, secondDir);
        return db;
    }

    private void createDir(String dir){
        File file = new File(dir);
        if(!file.exists() || !file.isDirectory()){
            file.mkdirs();
        }
    }

    @Bean("third")
    public   RocksDB initThirdRocksdb() throws RocksDBException{
        Options options = getOptions();
        createDir(thirdDir);
        final RocksDB db = RocksDB.open(options, thirdDir);
        return db;
    }

}
