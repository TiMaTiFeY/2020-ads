package ru.mail.polis.ads.timatifey.homework4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
Task text: https://www.e-olymp.com/ru/problems/4261
Task tests: https://www.e-olymp.com/ru/submissions/7556986
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

    public static int countMerge(Integer[] arr, int l, int r, int m) {
        Integer[] arrA = Arrays.copyOfRange(arr, l, m);
        Integer[] arrB = Arrays.copyOfRange(arr, m, r);
        int iA = 0;
        int iB = 0;
        int countInv = 0;
        for (int i = l; i < r; i++) {
            if (iA < arrA.length && (iB == arrB.length || arrA[iA] <= arrB[iB])) {
                arr[i] = arrA[iA++];
            } else {
                arr[i] = arrB[iB++];
                countInv += arrA.length - iA;
            }
        }
        return countInv;
    }

    public static int countInv(Integer[] a, int i, int j) {
        if (j - i <= 1) return 0;
        int m = (i + j) / 2;
        return countInv(a, i, m) + countInv(a, m, j) + countMerge(a, i, j, m);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            int n = in.nextInt();
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            out.write(String.valueOf(countInv(a, 0, n)));
        }
    }
}
