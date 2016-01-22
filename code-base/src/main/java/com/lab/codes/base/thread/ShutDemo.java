package com.lab.codes.base.thread;

public class ShutDemo {

  public static void main(String[] args) {
//    Thread t = new Thread() {
//      @Override
//      public void run() {
    shutHook(5);
        while (true) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            System.out.println("exit");
            break;
          }
        }
//      }
//    };
//    start(t);
  }

  private static void shutHook(final int n) {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        System.out.println("sleep");
        try {
          Thread.sleep(n * 1000);
        } catch (InterruptedException e1) {
          e1.printStackTrace();
        }
      }
    });
  }

  private static void start(Thread t) {
    t.start();
    shutHook(4);
  }

}
