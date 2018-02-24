package com.spring.boot.action.zk.constant;

public class GlobalConstants {
	// zk服务器列表
	public static final String zkhosts = "127.0.0.1:2181";
	// 连接的超时时间
	public static final int sessionTimeout = 2000;
	// 服务在zk下的路径    /Users/fgm/software/zookeeper/data
	public static final String parentZnodePath = "/servers";

}
