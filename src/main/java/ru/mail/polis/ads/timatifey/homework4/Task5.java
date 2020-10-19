package ru.mail.polis.ads.timatifey.homework4;

import java.io.*;
import java.util.List;
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

//    public static int[] mergeSort(int[] arr) {
//        if (arr.length == 1) {
//            return arr;
//        }
//        if (arr.length == 2) {
//            return arr[0] <= arr[1] ? arr : new int[] {arr[1], arr[0]};
//        }
//        int m = arr.length / 2;
//
//        int[] a = new int[m];
//        for (int i = 0; i < m; i++) a[i] = arr[i];
//
//        int[] b = new int[arr.length - m];
//        for (int i = 0; i < arr.length - m; i++) b[i] = arr[m + i];
//
//        int[] mergedA = mergeSort(a);
//        int[] mergedB = mergeSort(b);
//        int[] s = merge(mergedA, mergedB);
//        int[] clearS = new int[s.length - 1];
//
//        int k = s[0];
//        System.arraycopy(s, s.length, clearS, 1, s.length - 1);
//
//
//    }

    public static int[] merge(int[] arrA, int[] arrB) {
        int i = 0;
        int j = 0;
        int[] result = new int[arrA.length + arrB.length + 1];
        int k = 1;
        int countInv = 0;
        while (i < arrA.length || j < arrB.length) {
            if (i == arrA.length) {
                result[k++] = arrB[j++];
            } else if (j == arrB.length) {
                result[k++] = arrA[i++];
            } else if (arrA[i] <= arrB[j]) {
                result[k++] = arrA[i++];
            } else {
                result[k++] = arrB[j++];
                countInv = countInv + (arrA.length - i);
            }
        }
        result[0] = countInv;
        return result;
    }

//    public static int countMerge(int[] a, int i, int j, int m) {
//        int[] arrSorted = mergeSort(a);
//    }

//    public static int countInv(int[] a, int i, int j) {
//        if (j - i <= 1)
//            return 0;
//        int m = (i + j) / 2;
//        return countInv(a, i, m) + countInv(a, m + 1, j) + countMerge(a, i, j, m);
//    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();


            //out.write(String.valueOf(l));
        }
    }
}
