package ru.mail.polis.ads.timatifey.lesson20oct;

import java.io.*;
import java.util.StringTokenizer;

/*
Task text: https://www.e-olymp.com/ru/problems/3969
Task tests: https://www.e-olymp.com/ru/submissions/7548935
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

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        long w = in.nextInt();
        long h = in.nextInt();
        long n = in.nextInt();

        long l = Math.max(w, h);
        long r = Math.max(w, h) * n;
        while (l < r) {
            long m = (l + r) / 2;
            long count = (m / w) * (m / h);
            if (n <= count) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        System.out.println(l);
    }

}
