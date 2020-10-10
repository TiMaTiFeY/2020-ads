package ru.mail.polis.ads.timatifey.homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
Task text: https://www.e-olymp.com/ru/problems/5149
Task tests: https://www.e-olymp.com/ru/submissions/7484958
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] a = new int[n];

            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            int l = 0;
            int r = a[n - 1] - a[0] + 1;
            while (r - l > 1) {
                int m = (l + r) / 2;
                if (checkX(a, m, k)) {
                    l = m;
                } else {
                    r = m;
                }
            }
            out.write(String.valueOf(l));
        }
    }

    private static boolean checkX(int[] a, int x, int k) {
        int count = 1;
        int lastCord = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] - lastCord >= x) {
                count++;
                lastCord = a[i];
            }
        }
        return count >= k;
    }
}
