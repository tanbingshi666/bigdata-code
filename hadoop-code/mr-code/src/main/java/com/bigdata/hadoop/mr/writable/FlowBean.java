package com.bigdata.hadoop.mr.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import lombok.Data;
import org.apache.hadoop.io.Writable;


@Data
// 1 必须实现Writable接口
public class FlowBean implements Writable {

  private long upFlow;
  private long downFlow;
  private long sumFlow;

  // 2 反序列化时，需要反射调用空参构造函数，所以必须有空参构造
  public FlowBean() {
    super();
  }

  @Override
  // 3 重写序列化方法（注意反序列化的顺序和序列化的顺序完全一致）
  public void write(DataOutput out) throws IOException {
    out.writeLong(upFlow);
    out.writeLong(downFlow);
    out.writeLong(sumFlow);
  }

  @Override
  // 4 重写反序列化方法（注意反序列化的顺序和序列化的顺序完全一致）
  public void readFields(DataInput in) throws IOException {
    upFlow = in.readLong();
    downFlow = in.readLong();
    sumFlow = in.readLong();
  }

  //5 重写ToString
  @Override
  public String toString() {
    return upFlow + "\t" + downFlow + "\t" + sumFlow;
  }
}
