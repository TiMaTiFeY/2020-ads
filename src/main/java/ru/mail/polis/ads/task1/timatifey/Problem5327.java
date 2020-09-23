package ru.mail.polis.ads.task1.timatifey;

import java.io.*;
import java.util.StringTokenizer;

public class Problem5327 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String str = in.next();
        int size = str.length();
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (str.charAt(i) == '(')
                count++;
            else
                count--;
            if (count < 0)
                break;
        }
        out.write(count == 0 ? "YES" : "NO");
    }

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
            solve(in, out);
        }
    }
}
