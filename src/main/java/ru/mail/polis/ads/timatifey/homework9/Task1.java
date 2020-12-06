package ru.mail.polis.ads.timatifey.homework9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
Кратчайший путь
Task text: https://www.e-olymp.com/ru/problems/4856
Task tests: https://www.e-olymp.com/ru/submissions/7963221
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
            int m = in.nextInt();
            int start = in.nextInt() - 1;
            int finish = in.nextInt() - 1;
            final int INF = Integer.MAX_VALUE;

            int[][] graph = new int[n][n];
            for (int i = 0; i < m; i++) {
                int b = in.nextInt();
                int e = in.nextInt();
                int w = in.nextInt();
                graph[b - 1][e - 1] = w;
                graph[e - 1][b - 1] = w;
            }

            boolean[] visited = new boolean[n];
            int[] dist = new int[n];
            int[] prev = new int[n];
            for (int i = 0; i < n; i++) {
                dist[i] = INF;
                prev[i] = -1;
            }

            visited[start] = true;
            dist[start] = 0;
            int min_dist = 0;
            int min_vertex = start;

            while (min_dist < INF) {
                int curr = min_vertex;
                visited[curr] = true;
                for (int i = 0; i < n; i++) {
                    if (graph[curr][i] != 0) {
                        if (dist[curr] + graph[curr][i] < dist[i]) {
                            dist[i] = dist[curr] + graph[curr][i];
                            prev[i] = curr;
                        }
                    }
                }
                min_dist = INF;
                for (int j = 0; j < n; ++j) {
                    if (!visited[j] && dist[j] < min_dist) {
                        min_dist = dist[j];
                        min_vertex = j;
                    }
                }
            }
            if (dist[finish] != INF) {
                out.write(dist[finish] + "\n");
                List<Integer> path = new ArrayList<>();
                path.add(finish);
                int j = prev[finish];
                while (j != -1) {
                    path.add(j);
                    j = prev[j];
                }
                for (int i = path.size() - 1; i >= 0; i--) {
                    out.write((path.get(i) + 1) + " ");
                }
            } else {
                out.write("-1");
            }
        }
    }
}