package com.bigdata.hadoop.rpc.proto;

import org.apache.hadoop.ipc.ProtocolInfo;


@ProtocolInfo(protocolName = "com.bigdata.hadoop.rpc.proto.ResourceTrackerPB", protocolVersion = 1)
public interface ResourceTrackerPB extends ResourceTracker.ResourceTrackerService.BlockingInterface {
}
