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
			configuration = new Configuration();
			configuration.set("fs.defaultFS",defaultFS.trim());
			fileSystem = FileSystem.get(configuration);
			System.out.println("获得 FileSystem ");
		} catch (IOException e) {
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
