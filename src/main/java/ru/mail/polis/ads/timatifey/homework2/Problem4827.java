package ru.mail.polis.ads.timatifey.homework2;

import java.io.*;
import java.math.BigInteger;

public class Problem4827 {

    static BigInteger findOrderStatistic(BigInteger[] array, int k) {
        int left = 0, right = array.length - 1;
        while (true) {
            int mid = partition(array, left, right);
            if (mid == k) {
                return array[mid];
            } else if (k < mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }

    static int partition(BigInteger[] array, int l, int r) {
        BigInteger x = array[l];
        int i = l;
        int j = r;
        while (i <= j) {
            while (array[i].compareTo(x) > 0) i++;
            while (array[j].compareTo(x) < 0) j--;
            if (i >= j) break;
            BigInteger t = array[i];
            array[i] = array[j];
            array[j] = t;
        }
        return j;
    }

    public static void main(final String[] arg) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            int k = Integer.parseInt(in.readLine());
            String[] numbs = in.readLine().split("\\s+");
            int n = numbs.length;
            BigInteger[] arr = new BigInteger[n];
            for (int i = 0; i < n; i++) arr[i] = new BigInteger(numbs[i]);
            out.write(String.valueOf(findOrderStatistic(arr, k - 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}