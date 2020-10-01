package ru.mail.polis.ads.timatifey.homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

public class Problem1462 {
    public static void main(final String[] arg) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine());
        String[] nums = in.readLine().split("\\s+");
        LinkedList<Integer>[] listByDigits = new LinkedList[10];
        for (int i = 0; i < 10; i++) listByDigits[i] = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(nums[i]);
            listByDigits[x % 10].add(x);
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
        out.close();
    }
}
