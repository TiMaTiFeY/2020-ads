package ru.mail.polis.ads.timatifey.homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
Task text: https://www.e-olymp.com/ru/problems/262
Task tests: https://www.e-olymp.com/ru/submissions/7506644
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
            int[] stepCost = new int[n + 2];
            int[] d = new int[n + 2];
            stepCost[0] = 0;
            for (int i = 1; i < n + 1; i++)
                stepCost[i] = in.nextInt();
            stepCost[n + 1] = 0;
            int k = in.nextInt();

            d[0] = 0;
            for (int i = 1; i < n + 2; i++) {
                int currentMax = d[i - 1];
                for (int j = Math.max(i - k, 0); j < (i - 1); j++)
                    if (currentMax < d[j]) currentMax = d[j];
                d[i] = currentMax + stepCost[i];
            }

            out.write(String.valueOf(d[n + 1]));
        }
    }
}
