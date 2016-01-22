package com.lab.codes.base.sync;

import java.util.ArrayList;
import java.util.List;

public class Demo {
  public static SyncDemo syncDemo = null;
  public static SyncVarDemo syncVarDemo = null;
  public static List<Thread> threads = new ArrayList<Thread>();

  public static void main(String[] args) {
    for (int i = 0; i < 200; i++) {
      threads.add(new Thread() {
        @Override
        public void run() {
          syncVarDemo = SyncVarDemo.getInstance(this.getId());
        }
      });
    }

    System.out.println("start threads");
    for (Thread t : threads) {
      t.start();
    }

    System.out.println("main sleep ...");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("interrupt threads");
    for (Thread t : threads) {
      t.interrupt();
    }

    System.out.println("join threads");
    for (Thread t : threads) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
