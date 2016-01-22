package com.lab.codes.base.sync;

public class InSortDemo {
  static int x = 0,y = 0;
  static int a = 0,b = 0;

  /**
   * @param args
   */
  public static void main(String[] args) {
    InSortDemo demo = new InSortDemo();
    for (int i = 1; i <= 10; i++)
      demo.test(i);
  }

  private void test(int count) {
    for (int i = 0;i < 100000; i++) {
      Thread one = new Thread(new Runnable() {
        public void run() {
          a = 1;
          x = b;
        }
      });
      Thread other = new Thread(new Runnable() {
        public void run() {
          b = 1;
          y = a;
        }
      });

//      x = 0;
//      y = 0;
//      a = 0;
//      b = 0;

      one.start();
      other.start();
      while (Thread.activeCount() > 2) {
        Thread.yield();
      }
      if (x == 0 && y == 0) {
        System.out.printf(count + " : pass, (x, y) = (%s, %s)\n", x, y);
      }
    }
    System.out.println(count + " : end");
  }

}
