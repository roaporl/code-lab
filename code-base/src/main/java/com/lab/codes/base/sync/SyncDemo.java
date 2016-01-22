package com.lab.codes.base.sync;

public class SyncDemo {

  private static SyncDemo singletonInstance = null;

  private SyncDemo() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      System.out.println("Sleep Interrupted");
    }
  }

  public static synchronized SyncDemo getInstance(long i) {
    if (singletonInstance == null) {
      singletonInstance = new SyncDemo();
      System.out.println("new " + i);
    }
    return singletonInstance;
  }
}
