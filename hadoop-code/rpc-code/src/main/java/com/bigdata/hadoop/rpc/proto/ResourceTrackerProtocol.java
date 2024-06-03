package com.bigdata.hadoop.rpc.proto;

public interface ResourceTrackerProtocol {

  ResourceTrackerMessage.ResponseProto registerNodeManager(ResourceTrackerMessage.RequestProto request)
      throws Exception;
}
