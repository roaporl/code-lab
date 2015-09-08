package com.lab.codes.base.algorithm;


import com.google.common.base.Joiner;

public class HeapSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    //调整节点 大根堆
    @SuppressWarnings("unchecked")
    private void adjustHeapNode(T a[], int i, int n){   //调整节点i，数组共有n个节点
        if (n == 1 || i > (n-2)/2) return; //i为叶子节点  (n-2)/2 最后一个非叶子节点的位置

        int left = 2*i + 1;
        int right = 2*i + 2;

        if (right < n) {    //说明i有左右两个子节点         三个节点找最大值
            if (a[i].compareTo(a[left]) >= 0
                    && a[i].compareTo(a[right]) >=0 )      // i 最大 不用调整
                return;

            if (a[i].compareTo(a[left]) < 0
                    && a[right].compareTo(a[left]) <= 0) { // left 最大
                swap(a, left, i);
                adjustHeapNode(a, left, n);
                return;
            }

            if (a[i].compareTo(a[right]) < 0
                    && a[left].compareTo(a[right]) <= 0) { // right 最大
                swap(a, right, i);
                adjustHeapNode(a, right, n);
            }
        } else {
            // 说明i只有左节点   二个节点找最大值
            // left为最后一个节点
            if (a[i].compareTo(a[left]) < 0) {
                swap(a, left, i);
                adjustHeapNode(a, left, n);
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
            System.out.println(Joiner.on(", ").join(a));
            swap(a, n-1-i, 0);
            adjustHeapNode(a, 0, n-1-i);
        }
    }
}
