package com.lab.codes.base.algorithm;

public abstract class Sort<T extends Comparable<T>> {

    public abstract int compare(Object o1, Object o2);

    protected void swap(Class<? extends Comparable>[] a, int i, int j) {
        Class<? extends Comparable> tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    protected void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
