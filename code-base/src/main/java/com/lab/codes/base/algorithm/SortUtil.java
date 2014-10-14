package com.lab.codes.base.algorithm;

import com.google.common.base.Joiner;

public class SortUtil {
    public static Integer[] sourceArray = {4, 3, 6, 9, 2, 0, 1, 8, 7, 5, 3};


    public static void print(Integer[] a) {
        System.out.println(Joiner.on(",").join(a));
    }

    public static void main(String[] args) {
//        QuickSort quickSort = new QuickSort();
//        quickSort.sort(0, sourceArray.length - 1, sourceArray);

        HeapSort<Integer> heapSort = new HeapSort<Integer>();
        heapSort.heapSort(sourceArray, 5);
        print(sourceArray);
    }
}
