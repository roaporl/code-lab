package com.lab.codes.base.sync;

public class SyncVarDemo {

  private static SyncVarDemo singletonInstance = null;

  private SyncVarDemo() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      System.out.println("Sleep Interrupted");
    }
  }

  public static SyncVarDemo getInstance(long i) {
    if (singletonInstance == null) {
      synchronized (SyncVarDemo.class) {
        if (singletonInstance == null) {
          singletonInstance = new SyncVarDemo();
          System.out.println("new " + i);
        }
      }
    }
    return singletonInstance;
  }
}
