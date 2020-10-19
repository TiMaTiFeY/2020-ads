package ru.mail.polis.ads.timatifey.homework4;

import java.io.*;
import java.util.StringTokenizer;

/*
Task text: https://www.e-olymp.com/ru/problems/1618
Task tests: https://www.e-olymp.com/ru/submissions/7541524
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
        try (PrintWriter out = new PrintWriter(System.out)) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();

            int m = in.nextInt();
            int[] b = new int[m];
            for (int i = 0; i < m; i++) b[i] = in.nextInt();

            int[][] d = new int[n + 1][m + 1];

            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= m; j++)
                    d[i][j] = a[i - 1] == b[j - 1] ? d[i - 1][j - 1] + 1 : Math.max(d[i - 1][j], d[i][j - 1]);

            out.write(String.valueOf(d[n][m]));
        }
    }
}