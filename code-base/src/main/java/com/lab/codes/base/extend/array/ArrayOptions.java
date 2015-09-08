package com.lab.codes.base.extend.array;

import com.lab.codes.base.algorithm.QuickSort;

public class ArrayOptions {

  // micro ASG
  public static void findABC0(Integer[] a){
    // O(n*logn)
    QuickSort.quickSort1(a, 0, a.length);
    // O(n^2)
    for (int i = 0; i < a.length; ++i) {
      int begin = i + 1;
      int end = a.length - 1;
      while (begin < end) {
        if (a[begin] + a[end] > -a[i]) {
          --end;
        } else if (a[begin] + a[end] < -a[i]) {
          --begin;
        } else {
          System.out.printf("%d, %d, %d", a[i], a[begin], a[end]);
        }
      }
    }
  }

  public static void qsLinkList() {
    // todo
  }

}
