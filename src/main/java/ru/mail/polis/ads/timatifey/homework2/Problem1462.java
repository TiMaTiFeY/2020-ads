package ru.mail.polis.ads.timatifey.homework2;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Problem1462 {
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
            LinkedList<Integer>[] listByDigits = new LinkedList[10];
            for (int i = 0; i < 10; i++) listByDigits[i] = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                int lastDigit = x % 10;
                listByDigits[lastDigit].add(x);
            }

            for (int i = 0; i < 10; i++) {
                LinkedList<Integer> currentList = listByDigits[i];
                if (currentList.size() < 1) continue;
                if (i != 0) out.write(" ");
                if (currentList.size() == 1) out.write(String.valueOf(currentList.getFirst()));
                else {
                    Collections.sort(currentList);
                    int j = 0;
                    for (int number: currentList) {
                        if (j != 0) out.write(" ");
                        out.write(String.valueOf(number));
                        j++;
                    }
                }
            }
        }
    }
}
