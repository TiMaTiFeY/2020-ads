package ru.mail.polis.ads.timatifey.lesson20oct;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/*
Task text: https://www.e-olymp.com/ru/problems/2169
Task tests: https://www.e-olymp.com/ru/submissions/7548370
 */

public class Task1 {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            int n = in.nextInt();

            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = i + 1;
                b[i] = n - i;
            }
            if (n == 1) {
                out.write("1");
                return;
            }
            do {
                for (int i = 0; i < n; i++)
                    out.write(a[i] + " ");
                out.write("\n");

                int tailInd = n - 1;
                while (tailInd != 0 && a[tailInd - 1] > a[tailInd]) tailInd--;

                int swapIndBeforeTail = tailInd - 1;
                int swapIntAfterTail = n - 1;
                while (a[swapIntAfterTail] < a[swapIndBeforeTail]) swapIntAfterTail--;

                int t = a[swapIndBeforeTail];
                a[swapIndBeforeTail] = a[swapIntAfterTail];
                a[swapIntAfterTail] = t;

                int[] copyTail = Arrays.copyOfRange(a, tailInd, n);
                int j = copyTail.length - 1;
                for (int i = tailInd; i < n; i++) a[i] = copyTail[j--];

            } while (!Arrays.equals(a, b));
            for (int i = 0; i < n; i++)
                out.write(a[i] + " ");
            out.write("\n");
        }
    }
}
