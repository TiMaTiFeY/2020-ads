package ru.mail.polis.ads.timatifey.homework9;

import java.io.*;
import java.util.StringTokenizer;

/*
Форд-Беллман
Task text: https://www.e-olymp.com/ru/problems/1453
Task tests: https://www.e-olymp.com/ru/submissions/7963540
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
            int m = in.nextInt();
            int start = 0;

            int[][] edges = new int[m][3];

            for (int i = 0; i < m; i++) {
                int b = in.nextInt();
                int e = in.nextInt();
                int w = in.nextInt();
                edges[i] = new int[] { b - 1, e - 1, w };
            }

            final int INF = Integer.MAX_VALUE;
            int[] dist = new int[n];
            for (int i = 0; i < n; i++) {
                dist[i] = INF;
            }
            dist[start] = 0;

            while (true) {
                boolean any = false;
                for (int j = 0; j < m; ++j)
                    if (dist[edges[j][0]] < INF) {
                        if (dist[edges[j][1]] > dist[edges[j][0]] + edges[j][2]) {
                            dist[edges[j][1]] = dist[edges[j][0]] + edges[j][2];
                            any = true;
                        }
                    }
                if (!any) {
                    break;
                }
            }
            for (int i = 0; i < n; i++) {
                out.write((dist[i] != INF ? dist[i] : 30000) + " ");
            }
        }
    }
}