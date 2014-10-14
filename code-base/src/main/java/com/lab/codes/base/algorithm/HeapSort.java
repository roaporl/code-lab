package com.lab.codes.base.algorithm;


public class HeapSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    //调整节点 大根堆
    @SuppressWarnings("unchecked")
    private void adjustHeapNode(T a[], int i, int n){   //调整节点i，数组共有n个节点
        if (n == 1 || i > (n-2)/2) return; //i为叶子节点  (n-2)/2 最后一个非叶子节点的位置

        int iLeft = 2*i + 1;
        int iRight = 2*i + 2;

        if (iRight < n) {    //说明i有左右两个子节点         三个节点找最大值
            if (a[i].compareTo(a[iLeft]) >= 0
                    && a[i].compareTo(a[iRight]) >=0 )      // i 最大 不用调整
                return;

            if (a[i].compareTo(a[iLeft]) < 0
                    && a[iRight].compareTo(a[iLeft]) <= 0) { // iLeft 最大
                swap(a, iLeft, i);
                adjustHeapNode(a, iLeft, n);
                return;
            }

            if (a[i].compareTo(a[iRight]) < 0
                    && a[iLeft].compareTo(a[iRight]) <= 0) { // iRight 最大
                swap(a, iRight, i);
                adjustHeapNode(a, iRight, n);
            }
        } else {
             // 说明i只有左节点   二个节点找最大值
            //iLeft为最后一个节点
            if (a[i].compareTo(a[iLeft]) < 0) {
                swap(a, iLeft, i);
                adjustHeapNode(a, iLeft, n);
            }
        }
    }


    //建立堆
    public void createHeap(T a[], int n) {
        int iFirst = (n-1)/2; //第一个非叶子节点
        while (iFirst >= 0) {
            adjustHeapNode(a, iFirst--, n);
        }
    }

    //堆排序
    public void heapSort(T a[], int n) {
        createHeap(a, n);
        for (int i = 0; i <= n-1; i++) {
            swap(a, n-1-i, 0);
            adjustHeapNode(a, 0, n-1-i);
        }
    }
}
