package ru.mail.polis.ads.timatifey.homework9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
Циклы в графе
Task text: https://www.e-olymp.com/ru/problems/2022
Task tests: https://www.e-olymp.com/ru/submissions/7985837
 */

public class Task5 {
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
                graph.get(e - 1).add(b - 1);
            }
            DoDFS doDFS = new DoDFS(graph, n);
            if (doDFS.isHasLoop()) {
                out.write("Yes\n" + doDFS.getMinV());
            } else {
                out.write("No\n");
            }
        }
    }

    static class DoDFS {
        private final int[] color;
        private final List<List<Integer>> graph;
        private final int[] parent;
        private boolean hasLoop;
        private int minV;

        static final int WHITE = 0;
        static final int GRAY = 1;
        static final int BLACK = 2;

        DoDFS(List<List<Integer>> graph, int n) {
            this.graph = graph;
            this.color = new int[n];
            parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = -1;
            hasLoop = false;
            minV = n + 1;
            for (int i = 0; i < n; i++) {
                if (color[i] == WHITE) {
                    dfs(i);
                }
            }
        }

        public void dfs(int u) {
            color[u] = GRAY;
            for (int v: graph.get(u)) {
                if (parent[u] != v) {
                    if (color[v] == WHITE) {
                        parent[v] = u;
                        dfs(v);
                    } else {
                        if (color[v] == GRAY) {
                            int t = parent[v];
                            parent[v] = u;
                            hasLoop = true;
                            findMinV(v);
                            parent[v] = t;
                        }
                    }
                }
            }
            color[u] = BLACK;
        }

        public boolean isHasLoop() {
            return hasLoop;
        }

        public int getMinV() {
            return minV + 1;
        }

        public void findMinV(int v) {
            int curr = v;
            while (parent[curr] != v && parent[curr] != -1) {
                if (minV > curr) {
                    minV = curr;
                }
                curr = parent[curr];
            }
            if (minV > curr) {
                minV = curr;
            }
        }
    }
}