package ru.mail.polis.ads.timatifey.homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedOutputStream;

/*
Task text: https://www.e-olymp.com/ru/problems/4074
Task tests: https://www.e-olymp.com/ru/submissions/7490095
 */

public class Task3 {
    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try(BufferedOutputStream out = new BufferedOutputStream(System.out)) {
            HeapMedian heap = new HeapMedian(1_000_000);
            while (true) {
                int x = in.nextInt();
                heap.add(x);
                byte[] buffer = (heap.getMedian() + "\n").getBytes();
                out.write(buffer, 0, buffer.length);
            }
        } catch (Exception ignored) {
        }
    }
}

class HeapMedian {
    private final HeapMax leftHeap;
    private final HeapMin rightHeap;
    private int size = 0;

    HeapMedian(int size) {
        leftHeap = new HeapMax(size / 2);
        rightHeap = new HeapMin(size / 2);
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
    private final int[] heap;
    private int size = 0;

    HeapMax(int size) {
        heap = new int[size + 2];
    }

    public void swim(int k) {
        while (k > 1 && heap[k] > heap[k / 2]) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k; //left child
            if (j < size && heap[j] < heap[j + 1]) j++; //right child
            if (heap[k] >= heap[j]) break; //inv holds
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        int t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    public void insert(int v) {
        heap[++size] = v;
        swim(size);
    }

    public int delMax() {
        int max = heap[1];
        swap(1, size--);
        sink(1);
        return max;
    }

    public int checkMax() {
        return heap[1];
    }

    public int size() {
        return size;
    }
}

class HeapMin {
    private final int[] heap;
    private int size = 0;

    HeapMin(int size) {
        heap = new int[size + 2];
    }

    public void swim(int k) {
        while (k > 1 && heap[k] < heap[k / 2]) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k; //left child
            if (j < size && heap[j] > heap[j + 1]) j++; //right child
            if (heap[k] <= heap[j]) break; //inv holds
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        int t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    public void insert(int v) {
        heap[++size] = v;
        swim(size);
    }

    public int delMin() {
        int min = heap[1];
        swap(1, size--);
        sink(1);
        return min;
    }

    public int checkMin() {
        return heap[1];
    }

    public int size() {
        return size;
    }
}