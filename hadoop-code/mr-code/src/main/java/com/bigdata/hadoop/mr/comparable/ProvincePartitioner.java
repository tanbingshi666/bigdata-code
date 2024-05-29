package com.bigdata.hadoop.mr.comparable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class ProvincePartitioner extends Partitioner<FlowBean, Text> {

  @Override
  public int getPartition(FlowBean flowBean, Text text, int numPartitions) {
    //获取手机号前三位
    String phone = text.toString();
    String prePhone = phone.substring(0, 3);

    //定义一个分区号变量partition,根据prePhone设置分区号
    int partition;
    switch (prePhone) {
      case "136":
        partition = 0;
        break;
      case "137":
        partition = 1;
        break;
      case "138":
        partition = 2;
        break;
      case "139":
        partition = 3;
        break;
      default:
        partition = 4;
        break;
    }

    //最后返回分区号partition
    return partition;
  }
}
