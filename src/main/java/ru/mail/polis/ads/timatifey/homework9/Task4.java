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
Топологическая сортировка
Task text: https://www.e-olymp.com/ru/problems/1948
Task tests: https://www.e-olymp.com/ru/submissions/7980606
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
            int m = in.nextInt();

            List<List<Integer>> graph = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                int b = in.nextInt();
                int e = in.nextInt();
                graph.get(b - 1).add(e - 1);
            }

            DoDFS doDFS = new DoDFS(graph, n);
            if (doDFS.hasLoop()) {
                out.write("-1");
            } else {
                for (int i = doDFS.topSort().size() - 1; i >= 0; i--) {
                    out.write(doDFS.topSort().get(i) + " ");
                }
            }

        }
    }

    static class DoDFS {
        private final int[] color;
        private final List<List<Integer>> graph;
        private boolean hasLoop;
        private final List<Integer> sortedGraph = new ArrayList<>();

        static final int WHITE = 0;
        static final int GRAY = 1;
        static final int BLACK = 2;

        DoDFS(List<List<Integer>> graph, int n) {
            this.graph = graph;
            this.color = new int[n];
            hasLoop = false;
            for (int i = 0; i < n; i++) {
                if (color[i] == WHITE) {
                    dfs(i);
                }
            }
        }

        public void dfs(int u) {
            color[u] = GRAY;
            for (int v: graph.get(u)) {
                if (color[v] == WHITE) {
                    dfs(v);
                } else {
                    if (color[v] == GRAY) {
                        hasLoop = true;
                        break;
                    }
                }
            }
            sortedGraph.add(u + 1);
            color[u] = BLACK;
        }

        public boolean hasLoop() {
            return hasLoop;
        }

        public List<Integer> topSort() {
            return sortedGraph;
        }

    }
}
