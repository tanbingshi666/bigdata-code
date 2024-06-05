package com.bigdata.zookeeper.cases.ServerDynamicOnline;

import java.io.IOException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;


public class DistributeServer {

  private static final String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
  private static final int sessionTimeout = 2000;
  private ZooKeeper zk = null;
  private final String parentNode = "/servers";

  // 创建到 zk 的客户端连接
  public void getConnect() throws IOException {
    zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
      @Override
      public void process(WatchedEvent event) {
      }
    });
  }

  // 注册服务器
  public void registryServer(String hostname) throws Exception {
    String create =
        zk.create(parentNode + "/server", hostname.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    System.out.println(hostname + " is online " + create);
  }

  // 业务功能
  public void doBusiness(String hostname) throws Exception {
    System.out.println(hostname + " is working ...");
    Thread.sleep(Long.MAX_VALUE);
  }

  public static void main(String[] args) throws Exception {
    // 1 获取 zk 连接
    DistributeServer server = new DistributeServer();
    server.getConnect();

    // 2 利用 zk 连接注册服务器信息 eg: hadoop102
    server.registryServer(args[0]);

    // 3 启动业务功能
    server.doBusiness(args[0]);
  }
}
