package com.bigdata.hadoop.mr.comparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;


public class FlowMapper extends Mapper<LongWritable, Text, FlowBean, Text> {
  private final FlowBean outK = new FlowBean();
  private final Text outV = new Text();

  @Override
  protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

    //1 获取一行数据
    String line = value.toString();

    //2 按照"\t",切割数据
    String[] split = line.split("\t");

    //3 封装outK outV
    outK.setUpFlow(Long.parseLong(split[split.length - 3]));
    outK.setDownFlow(Long.parseLong(split[split.length - 2]));
    outK.setSumFlow();
    outV.set(split[0]);

    //4 写出outK outV
    context.write(outK, outV);
  }
}
