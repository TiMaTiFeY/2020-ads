package ru.mail.polis.ads.timatifey.homework9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
Кратчайший путь
Task text: https://www.e-olymp.com/ru/problems/4853
Task tests: https://www.e-olymp.com/ru/submissions/7963464
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
            int n = in.nextInt();
            int m = in.nextInt();
            int start = in.nextInt() - 1;
            int finish = in.nextInt() - 1;
            final int INF = Integer.MAX_VALUE;

            List<List<Integer>> graph = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                int b = in.nextInt();
                int e = in.nextInt();
                graph.get(b - 1).add(e - 1);
                graph.get(e - 1).add(b - 1);
            }

            int[] dist = new int[n];
            int[] prev = new int[n];
            for (int i = 0; i < n; i++) {
                dist[i] = INF;
                prev[i] = -1;
            }

            dist[start] = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(start);
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (Integer neighbour: graph.get(curr)) {
                    if (dist[curr] + 1 < dist[neighbour]) {
                        dist[neighbour] = dist[curr] + 1;
                        prev[neighbour] = curr;
                        queue.add(neighbour);
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