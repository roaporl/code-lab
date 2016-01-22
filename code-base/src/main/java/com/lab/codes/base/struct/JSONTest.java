package com.lab.codes.base.struct;

import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Stopwatch;
import org.json.JSONException;


public class JSONTest {

  public String id;
  public String name;
  public Boolean is;

  public static void main(String[] args) throws JSONException {
    Pattern pattern = Pattern.compile("(\\r)|(\\n)|(\\\\r)|(\\\\\n)");
    System.out.println(pattern.matcher("\\r").matches());
    System.out.println(Pattern.compile("\r").matcher("\r").matches());
    boolean m = "\\".matches("\\\\");
    System.out.println(m);

    JSONTest schematicAppInfo = new JSONTest();
    schematicAppInfo.id = "1";
    schematicAppInfo.name = "A";
    schematicAppInfo.is = true;

    String s1 = ((JSON) JSON.toJSON(schematicAppInfo)).toJSONString();
    System.out.println(s1);

    double max = 10E4;
    Stopwatch sw = Stopwatch.createStarted();
    org.json.JSONObject jsonObject = new org.json.JSONObject(s1);
    for (int i = 0; i < max; i++) {
      jsonObject.toString(2);
    }
    System.out.println(sw.stop().toString());
    System.out.println(jsonObject.toString(4));

    sw.reset().start();
    for (int i = 0; i < max; i++) {
      JSON.toJSONString(schematicAppInfo, SerializerFeature.PrettyFormat);
    }
    System.out.println(sw.stop().toString());
    System.out.println(JSON.toJSONString(schematicAppInfo, SerializerFeature.PrettyFormat));
  }
}
