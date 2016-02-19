package com.lab.rpc.proxy;

import java.util.concurrent.TimeUnit;

import com.codahale.metrics.Clock;
import com.codahale.metrics.MetricRegistry;
import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AbstractCounter {


  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCounter.class);
  private final MetricRegistry metricRegistry = new MetricRegistry();

  private PerfCounterReporter reporter;
  private final Joiner joiner = Joiner.on('-');
  private Clock clock = Clock.defaultClock();

  public AbstractCounter() {
    String host = CommonUtils.getLocalHost();
    try {
      reporter = PerfCounterReporter.forRegistry(metricRegistry).name("CNT-" + host).build();
      reporter.start(1, TimeUnit.MINUTES);
    } catch (Exception e) {
      LOGGER.error("PerfCounterReporter started failed : {}", Throwables.getStackTraceAsString(e));
    }
  }

  public void mark(String... tags) {
    metricRegistry.meter(joiner.join(tags) + "-counter").mark();
  }

  public void time(long start, String... tags) {
    long nano = clock.getTick() - start;
    for (String tag : tags) {
      metricRegistry.timer(tag + "-timer").update(nano, TimeUnit.NANOSECONDS);
    }
  }

  public long getTick() {
    return clock.getTick();
  }

  public double getOneMinuteRate(String... tags) {
    return metricRegistry.timer(joiner.join(tags) + "-timer").getOneMinuteRate();
  }

  public double getOneMinuteRate(String name) {
    return metricRegistry.timer(name + "|timer").getOneMinuteRate();
  }

  public void close() {
    reporter.close();
  }
}
