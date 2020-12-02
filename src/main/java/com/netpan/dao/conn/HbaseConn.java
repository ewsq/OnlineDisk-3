package com.netpan.dao.conn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class HbaseConn {
	private Configuration configuration = null;
	private Connection connection = null;
	private String quorum="hbslaver01,hbslaver02,hbslaver03";
    private String rootdir="hdfs://hbmaster:9000/hbase";
    
    private static class SingletonHolder{
    	private static final HbaseConn INSTANCE = new HbaseConn();
    }
    
    private HbaseConn(){
    	try {
    		configuration = HBaseConfiguration.create();
        	configuration.set("hbase.zookeeper.quorum", quorum);
        	configuration.set("hbase.rootdir", rootdir);
        	connection = ConnectionFactory.createConnection(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static final Connection getConn() {
    	return SingletonHolder.INSTANCE.connection;
    }
}