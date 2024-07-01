package com.bigdata.flume.interceptor.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONException;


public class JSONUtil {
  public static boolean isJSONValidate(String log) {
    try {
      JSONObject.parseObject(log);
      return true;
    } catch (JSONException e) {
      return false;
    }
  }
}
