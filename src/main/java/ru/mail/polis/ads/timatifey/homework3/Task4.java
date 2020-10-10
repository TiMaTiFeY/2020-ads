package ru.mail.polis.ads.timatifey.homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
Task text: https://www.e-olymp.com/ru/problems/9016
Task tests: https://www.e-olymp.com/ru/submissions/7484208
 */

public class Task4 {
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
            int q = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            for (int i = 0; i < q; i++) {
                int x = in.nextInt();
                int l = 0;
                int r = n - 1;
                boolean inArray = false;
                while (l <= r) {
                    int m = (l + r) / 2;
                    if (x == a[m]) {
                        inArray = true;
                        break;
                    }
                    if (x < a[m])
                        r = m - 1;
                    else
                        l = m + 1;
                }
                out.write(inArray ? "YES\n" : "NO\n");
            }
        }
    }
}
