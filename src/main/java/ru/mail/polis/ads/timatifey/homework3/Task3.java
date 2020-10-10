package ru.mail.polis.ads.timatifey.homework3;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Task text: https://www.e-olymp.com/ru/problems/4074
Task tests: https://www.e-olymp.com/ru/submissions/7485232
 */

public class Task3 {
    public static void main(final String[] arg) {
        Scanner sc = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            HeapMedian heap = new HeapMedian();
            while (sc.hasNextInt()) {
                int x = sc.nextInt();
                heap.add(x);
                out.write(heap.getMedian() + "\n");
            }
        }
    }
}

class HeapMedian {
    private HeapMax leftHeap;
    private HeapMin rightHeap;
    private int size = 0;

    HeapMedian() {
        leftHeap = new HeapMax();
        rightHeap = new HeapMin();
    }

    public void add(int x) {
        if (size == 0) {
            leftHeap.insert(x);
            size++;
            return;
        }
        if (leftHeap.size() == rightHeap.size()) {
            if (x > rightHeap.checkMin()) {
                int t = rightHeap.delMin();
                leftHeap.insert(t);
                rightHeap.insert(x);
            } else {
                leftHeap.insert(x);
            }
        } else {
            if (x > leftHeap.checkMax()) {
                rightHeap.insert(x);
            } else {
                int t = leftHeap.delMax();
                leftHeap.insert(x);
                rightHeap.insert(t);
            }
        }
        size++;
    }

    public int getMedian() {
        if (leftHeap.size() == rightHeap.size())
            return (leftHeap.checkMax() + rightHeap.checkMin()) / 2;
        return leftHeap.checkMax();
    }
}

class HeapMax {
    private final List<Integer> heap;
    private int size = 0;

    HeapMax() {
        heap = new ArrayList();
        heap.add(0);
    }

    public void swim(int k) {
        while (k > 1 && heap.get(k) > heap.get(k / 2)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k; //left child
            if (j < size && heap.get(j) < heap.get(j + 1)) j++; //right child
            if (heap.get(k) >= heap.get(j)) break; //inv holds
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        int t = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, t);
    }

    public void insert(int v) {
        size++;
        if (size <= heap.size() - 1)
            heap.set(size, v);
        else
            heap.add(v);
        swim(size);
    }

    public int delMax() {
        int max = heap.get(1);
        swap(1, size--);
        sink(1);
        return max;
    }

    public int checkMax() {
        return heap.get(1);
    }

    public int size() {
        return size;
    }
}

class HeapMin {
    private final List<Integer> heap;
    private int size = 0;

    HeapMin() {
        heap = new ArrayList();
        heap.add(0);
    }

    public void swim(int k) {
        while (k > 1 && heap.get(k) < heap.get(k / 2)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k; //left child
            if (j < size && heap.get(j) > heap.get(j + 1)) j++; //right child
            if (heap.get(k) <= heap.get(j)) break; //inv holds
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        int t = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, t);
    }

    public void insert(int v) {
        size++;
        if (size <= heap.size() - 1)
            heap.set(size, v);
        else
            heap.add(v);
        swim(size);
    }

    public int delMin() {
        int min = heap.get(1);
        swap(1, size--);
        sink(1);
        return min;
    }

    public int checkMin() {
        return heap.get(1);
    }

    public int size() {
        return size;
    }
}