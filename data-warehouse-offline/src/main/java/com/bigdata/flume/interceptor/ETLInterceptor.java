package com.bigdata.flume.interceptor;

import com.bigdata.flume.interceptor.utils.JSONUtil;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;


public class ETLInterceptor implements Interceptor {

  @Override
  public void initialize() {
  }

  @Override
  public Event intercept(Event event) {

    byte[] body = event.getBody();
    String log = new String(body, StandardCharsets.UTF_8);
    if (JSONUtil.isJSONValidate(log)) {
      return event;
    } else {
      return null;
    }
  }

  @Override
  public List<Event> intercept(List<Event> list) {

    list.removeIf(next -> intercept(next) == null);
    return list;
  }

  public static class Builder implements Interceptor.Builder {

    @Override
    public Interceptor build() {
      return new ETLInterceptor();
    }

    @Override
    public void configure(Context context) {
    }
  }

  @Override
  public void close() {
  }
}
