package com.lab.codes.base.sync;

public class VolatileTest {

  public static class VolatileExample {
    int x = 0;
    volatile boolean v = false;
    int count = 0;
    public void writer() {
      x = 42;
      v = true;
    }

    public void reader() {
      if (v) {
        //uses x - guaranteed to see 42.
//        System.out.println("read x : " + x);
        increase();
      }
    }

    private synchronized void increase() {
      count++;
    }
  }

  public static void main(String[] args) {
    VolatileTest test = new VolatileTest();
    for (int i = 0; i < 1000; i++)
      test.round();
  }

  private void round() {
    final VolatileExample example = new VolatileExample();
    final int max = 1000;
    Thread writeThread = new Thread(new Runnable() {
      @Override
      public void run() {
        example.writer();
      }
    });
    Thread readThread = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < max; i++)
          example.reader();
      }
    });

    writeThread.start();
    readThread.start();
    while (writeThread.isAlive()) {
      Thread.yield();
    }

    while (Thread.activeCount() > 2) {
      Thread.yield();
    }

    if (example.count < max) {
      System.out.println(example.count);
    }
  }
}
