package com.bigdata.hadoop.rpc.proto.server;

import com.google.protobuf.BlockingService;
import com.bigdata.hadoop.rpc.proto.ResourceTracker;
import com.bigdata.hadoop.rpc.proto.ResourceTrackerPB;
import com.bigdata.hadoop.rpc.proto.ResourceTrackerProtocolImpl;
import com.bigdata.hadoop.rpc.proto.ResourceTrackerServerSidePB;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;


public class ProtobufRpcServer {

  public static void main(String[] args) throws IOException {

    Configuration conf = new Configuration();
    String hostname = "localhost";
    int port = 9998;
    // 设置 rpc 引擎为 ProtobufRpcEngine
    RPC.setProtocolEngine(conf, ResourceTrackerPB.class, ProtobufRpcEngine.class);

    // 构建 Rpc Server
    RPC.Server server = new RPC.Builder(conf)
        // 通信协议
        .setProtocol(ResourceTrackerPB.class)
        // 通信协议实例
        .setInstance((BlockingService) ResourceTracker.ResourceTrackerService.newReflectiveBlockingService(
            new ResourceTrackerServerSidePB(new ResourceTrackerProtocolImpl())))
        // 服务端主机和端口
        .setBindAddress(hostname).setPort(port)
        // 服务端处理线程
        .setNumHandlers(1).setVerbose(true).build();

    // Rpc Server 启动
    server.start();
  }
}
