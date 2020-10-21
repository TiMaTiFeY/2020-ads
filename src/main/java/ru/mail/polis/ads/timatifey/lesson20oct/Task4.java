package ru.mail.polis.ads.timatifey.lesson20oct;

import java.io.*;
import java.util.StringTokenizer;

/*
Task text: https://www.e-olymp.com/ru/problems/3968
Task tests: https://www.e-olymp.com/ru/submissions/7557275
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
    final static double eps = 0.0000001;

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        double c = Double.parseDouble(in.next());
        double l = 0;
        double r = c;
        while (Math.abs(r - l) >= eps) {
            double m = (l + r) / 2;
            if (check(m, c)) {
                r = m;
            } else {
                l = m;
            }
        }
        System.out.printf("%f", l);
    }

    public static boolean check(double x, double c) {
        double res = x * x + Math.sqrt(x);
        return c - res < eps;
    }
}
