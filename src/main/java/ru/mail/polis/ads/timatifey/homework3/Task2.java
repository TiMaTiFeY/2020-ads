package ru.mail.polis.ads.timatifey.homework3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
Task text: https://www.e-olymp.com/ru/problems/4039
Task tests: https://www.e-olymp.com/ru/submissions/7484169
 */

public class Task2 {
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
        try (PrintWriter out = new PrintWriter(System.out)) {
            int n = in.nextInt();
            Heap heap = new Heap();
            for (int i = 0; i < n; i++) {
                int command = in.nextInt();
                switch (command) {
                    case 0: {
                        int x = in.nextInt();
                        heap.insert(x);
                        break;
                    }
                    case 1: {
                        int max = heap.delMax();
                        out.write(max + "\n");
                        break;
                    }
                }
            }
        }
    }
}

class Heap {
    private final List<Integer> heap;
    private int size = 0;

    Heap() {
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
}