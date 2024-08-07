package com.bigdata.hadoop.mr.comparable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;


public class FlowReducer extends Reducer<FlowBean, Text, Text, FlowBean> {
  @Override
  protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

    //遍历values集合,循环写出,避免总流量相同的情况
    for (Text value : values) {
      //调换KV位置,反向写出
      context.write(value, key);
    }
  }
}
