package ru.mail.polis.ads.timatifey.homework3;

import java.io.*;
import java.util.StringTokenizer;

/*
Task text: https://www.e-olymp.com/ru/problems/3737
Task tests: https://www.e-olymp.com/ru/submissions/7473332
 */

public class Task1 {
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
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            boolean isHeap = true;
            for (int i = 1; i <= n; i++) {
                if (2 * i <= n) {
                    if (!(a[i - 1] <= a[2 * i - 1])) {
                        isHeap = false;
                        break;
                    }
                }
                if (2 * i + 1 <= n) {
                    if (!(a[i - 1] <= a[2 * i + 1 - 1])) {
                        isHeap = false;
                        break;
                    }
                }
            }
            out.write(isHeap ? "YES" : "NO");
        }
    }
}
