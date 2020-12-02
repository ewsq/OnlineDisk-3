package com.netpan.dao.conn;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapred.JobConf;

public class HdfsConn {
	private FileSystem fileSystem = null;
	private Configuration configuration = null;
	private String defaultFS="hdfs://hbmaster:9000";

	private static class SingletonHolder {
		private static final HdfsConn INSTANCE = new HdfsConn();
	}

	private HdfsConn() {
		try {
			//System.setProperty("hadoop.home.dir", "D:\\hadoop-3.3.0");
			URI uri = new URI(defaultFS.trim());
			configuration = new Configuration();

			//JobConf conf = new JobConf(HdfsConn.class);
			//conf.setJobName("HdfsConn");

			//在这一步出错，暂没有调通，但这段代码在其它地方正常使用，在此处会进入反射错误，并且不直接报错
			fileSystem = FileSystem.get(uri,configuration,"root");

			System.out.println("获得 FileSystem ");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FileSystem getFileSystem() {
		FileSystem fs=SingletonHolder.INSTANCE.fileSystem;
		return fs;
	}
	
	public static Configuration getConfiguration() {
		return SingletonHolder.INSTANCE.configuration;
	}
	
	public static void main(String[] args) {
		System.out.println(getFileSystem());
	}
}
