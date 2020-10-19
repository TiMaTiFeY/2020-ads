package ru.mail.polis.ads.timatifey.homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
Task text: https://www.e-olymp.com/ru/problems/15
Task tests: https://www.e-olymp.com/ru/submissions/7506634
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            int m = in.nextInt();
            int n = in.nextInt();
            int[][] board = new int[m][n];
            int[][] d = new int[m][n];
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    board[i][j] = in.nextInt();

            d[m - 1][0] = board[m - 1][0];
            for (int i = m - 1 - 1; i >= 0; i--)
                d[i][0] = d[i + 1][0] + board[i][0];
            for (int j = 1; j < n; j++)
                d[m - 1][j] = d[m - 1][j - 1] + board[m - 1][j];

            for (int j = 1; j < n; j++)
                for (int i = m - 1 - 1; i >= 0; i--)
                    d[i][j] = Math.max(d[i][j - 1], d[i + 1][j]) + board[i][j];

            int i = 0, j = n - 1;
            StringBuilder sb = new StringBuilder();
            while (!(i == m - 1 && j == 0)) {
                int dif = d[i][j] - board[i][j];
                if ((j != 0) && (dif == d[i][j - 1])) {
                    sb.append("R");
                    j--;
                } else {
                    sb.append("F");
                    i++;
                }
            }
            out.write(sb.reverse().toString());
        }
    }
}