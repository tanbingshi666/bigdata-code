package com.bigdata.hadoop.rpc.writable;

public interface BusinessProtocol {

  // 版本 ID (必须)
  long versionID = 123456L;

  void mkdirs(String path);

  String getName(String name);
}
