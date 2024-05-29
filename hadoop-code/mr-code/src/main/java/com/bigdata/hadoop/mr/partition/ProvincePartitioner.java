package com.bigdata.hadoop.mr.partition;

import com.bigdata.hadoop.mr.writable.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class ProvincePartitioner extends Partitioner<Text, FlowBean> {

  @Override
  public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
    // 1 获取手机号前三位prePhone
    String phone = text.toString();
    String prePhone = phone.substring(0, 3);

    // 2 定义一个分区号变量partition,根据prePhone设置分区号
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

    // 3 最后返回分区号partition
    return partition;
  }
}
