package com.lab.codes.base.algorithm;


import java.util.Stack;

/**
 * Created by lab on 2014/10/12.
 */
public class QuickSort implements Sort {
    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    public Integer[] a = {4,3,6,9,2,0,1,7,5,3};

    private void swap(Integer[] a, int i, int j) {
        Integer tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private void quickSort1(Integer[] a, int left, int right) {
        if (left<right) {
            int p = partition(a, left, right);
            quickSort1(a, left, p-1);
            quickSort1(a, p+1, right);
        }
    }

    // none recursive
    private void quickSort2(Integer[] a, int left, int right) {
        Stack<Integer> t = new Stack<Integer>();
        if (left < right){
            // 初始化辅助栈间
            stackProduce(t, a, left, right);
            while (!t.empty()) {
                // stackConsumer
                int r = t.pop();
                int l = t.pop();
                // stackProducer
                stackProduce(t, a, l, r);
            }
        }
    }

    private void stackProduce(Stack<Integer> t, Integer[] a, int left, int right) {
        int p = partition(a, left, right);
        if (p-1 > left){
            t.push(left);
            t.push(p-1);
        }
        if (p+1 < right){
            t.push(p+1);
            t.push(right);
        }
    }

    private int partition(Integer[] a, int left, int right){
        int x = a[right];
        int i = left, j = right;
        while (true) {
            while (i < j && a[i] <= x) i++;
            while (j > i && a[j] >= x) --j;
            if (i < j) {
                swap(a, i, j);
            } else break;
        }
        swap(a, i, right);
        return i;
    }


    public void sort(int low, int high, Integer[] array) {
        int l = low;
        int h = high;
        int flag = array[low];

        while (l < h) {
            while(l < h && array[h] >= flag) h--;
            if (l < h) {
                int tmp = array[l];
                array[l] = array[h];
                array[h] = tmp;
                ++l;
            }
            while (l < h && array[l] <= flag) l++;
            if (l < h) {
                int tmp = array[l];
                array[l] = array[h];
                array[h] = tmp;
                --h;
            }
        }
        SortUtil.print(array);
        System.out.print("l="+(l+1)+"h="+(h+1)+"flag="+flag+"\n");
        if(l>low) sort(low,h-1, array);
        if(h<high) sort(l+1,high, array);
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort2(quickSort.a, 0, quickSort.a.length-1);
        SortUtil.print(quickSort.a);
    }
}
