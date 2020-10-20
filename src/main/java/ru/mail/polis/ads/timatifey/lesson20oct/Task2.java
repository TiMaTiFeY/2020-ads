package ru.mail.polis.ads.timatifey.lesson20oct;

import java.io.*;
import java.util.StringTokenizer;

/*
Task text: https://www.e-olymp.com/ru/problems/264
Task tests: https://www.e-olymp.com/ru/submissions/7548740
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

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            int n = in.nextInt();
            int[] a = new int[n];
            int[] d = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            if (n == 1) {
                out.write("1");
                return;
            }

            d[0] = 1;
            int maxD = 1;
            for (int i = 0; i < n; i++) {
                int currMax = 0;
                for (int j = 0; j < i; j++) {
                    if (d[j] > currMax) {
                        if (a[j] != 0)
                            if (a[i] % a[j] == 0)
                                currMax = d[j];
                    }
                    d[i] = currMax + 1;
                    if (d[i] > maxD) maxD = d[i];
                }

            }
            out.write(String.valueOf(maxD));
        }
    }

}
