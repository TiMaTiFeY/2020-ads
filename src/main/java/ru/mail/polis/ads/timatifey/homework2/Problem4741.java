package ru.mail.polis.ads.timatifey.homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Problem4741 {
    public static void main(final String[] arg) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine());
        String[] nums = in.readLine().split("\\s+");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(nums[i]);

        int res = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int t = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = t;
                    res++;
                }
            }
        }
        out.write(String.valueOf(res));
        out.close();
    }
}
