package ru.mail.polis.ads.timatifey.homework4;

import java.io.*;
import java.util.StringTokenizer;

/*
Task text: https://www.e-olymp.com/ru/problems/4261
Task tests:
 */

public class Task5 {
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

    public static InvMerge countMergeSort(int[] arr, int l, int r, int m) {
        if (r - l + 1 == 1) {
            return new InvMerge(arr, 0);
        }
        if (r - l + 1 == 2) {
            return arr[0] <= arr[1] ? new InvMerge(arr, 0) : new InvMerge(new int[] {arr[1], arr[0]}, 1);
        }
        int[] a = new int[m - l + 1];
        for (int i = 0; i < m - l + 1; i++) a[i] = arr[i + l];
        int[] b = new int[r - m];
        for (int i = 0; i < r - m; i++) b[i] = arr[m + i + 1];
        return countMerge(
                countMergeSort(a, 0, m - l, (m - l) / 2).array,
                countMergeSort(b, 0, r - m - 1, (r - m - 1) / 2).array
        );
    }

    public static InvMerge countMerge(int[] arrA, int[] arrB) {
        int i = 0;
        int j = 0;
        int[] result = new int[arrA.length + arrB.length];
        int k = 0;
        int countInv = 0;
        while (i < arrA.length || j < arrB.length) {
            if (i == arrA.length) {
                result[k++] = arrB[j++];
            } else if (j == arrB.length) {
                result[k++] = arrA[i++];
            } else if (arrA[i] <= arrB[j]) {
                result[k++] = arrA[i++];
            } else {
                countInv = countInv + (arrA.length - i);
                result[k++] = arrB[j++];
            }
        }
        return new InvMerge(result, countInv);
    }

    public static int countInv(int[] a, int i, int j) {
        if (j - i <= 0) return 0;
        int m = (i + j) / 2;
        return countInv(a, i, m) + countInv(a, m + 1, j) + countMergeSort(a, i, j, m).invCount;
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            out.write(String.valueOf(countInv(a, 0, n - 1)));
        }
    }

    static class InvMerge {
        int[] array;
        int invCount;
        InvMerge(int[] array, int invCount) {
            this.array = array;
            this.invCount = invCount;
        }
    }
}
