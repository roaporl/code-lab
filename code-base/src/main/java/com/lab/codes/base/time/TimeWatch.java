package com.lab.codes.base.time;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class TimeWatch {

  private Map<String, Long> tagCosts;
  private long total;
  public float MILLION = 1000.0f * 1000.0f;
  public float HUNDRED = 100.0f;
  public Stopwatch stopwatch;

  private TimeWatch() {
    total = 0;
    tagCosts = new HashMap<>();
    stopwatch = Stopwatch.createStarted();
  }

  public static TimeWatch createStarted() {
    return new TimeWatch();
  }

  public long addCost(String tag) {
    long interval = step();
    total += interval;
    Long val = tagCosts.get(tag);
    val = val == null ? interval : val + interval;
    tagCosts.put(tag, val);
    return interval;
  }

  private long step() {
    stopwatch.stop();
    long interval = stopwatch.elapsed(TimeUnit.NANOSECONDS);
    stopwatch.reset().start();
    return interval;
  }


  public String toString() {
    List<String> result = new ArrayList<>();
    result.add("TOTAL=" + String.format("%3.2fms", total / MILLION));
    for (Map.Entry<String, Long> entry : tagCosts.entrySet()) {
      long time = entry.getValue();
      result.add(entry.getKey() + "=" + String.format("%3.2fms", time / MILLION)
                 + "(" + String.format("%4.2f%%", time * HUNDRED / total) + ")");
    }
    return result.toString();
  }

  public TimeWatch stop() {
    if (!stopwatch.isRunning()) {
      stopwatch.stop();
    }
    return this;
  }

  public long getMillis(String tag) {
    return TimeUnit.NANOSECONDS.toMillis(tagCosts.get(tag));
  }

}
