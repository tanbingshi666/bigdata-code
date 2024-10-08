package com.bigdata.hadoop.mr.partition;

import com.bigdata.hadoop.mr.writable.FlowBean;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
  private final FlowBean outV = new FlowBean();

  @Override
  protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

    long totalUp = 0;
    long totalDown = 0;

    //1 遍历values,将其中的上行流量,下行流量分别累加
    for (FlowBean flowBean : values) {
      totalUp += flowBean.getUpFlow();
      totalDown += flowBean.getDownFlow();
    }

    //2 封装outKV
    outV.setUpFlow(totalUp);
    outV.setDownFlow(totalDown);
    outV.setSumFlow(totalDown + totalUp);

    //3 写出outK outV
    context.write(key, outV);
  }
}
