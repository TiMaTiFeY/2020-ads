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
            }
            else if (k < mid) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
    }

    static int partition(BigInteger[] array, int l, int r) {
        BigInteger x = array[r];
        int i = l;
        int j = r;
        int p = l - 1;
        int q = r + 1;
        while (i <= j) {
            while (array[i].compareTo(x) > 0) i++;
            while (array[j].compareTo(x) < 0) j--;
            if (i >= j) break;

            BigInteger t = array[i];
            array[i] = array[j];
            array[j] = t;

            if (array[i].compareTo(x) == 0) {
                p++;
                t = array[i];
                array[i] = array[p];
                array[p] = t;
            }
            i++;

            if (array[j].compareTo(x) == 0) {
                q--;
                t = array[q];
                array[q] = array[j];
                array[j] = t;
            }
            j--;
        }
        BigInteger t = array[i];
        array[i] = array[r];
        array[r] = t;

        j = i - 1;
        i++;
        for (int k = l; k <= p; k++, j--) {
            t = array[k];
            array[k] = array[j];
            array[j] = t;
        }
        for (int k = r - 1; k >= q; k--, i++) {
            t = array[k];
            array[k] = array[i];
            array[i] = t;
        }
        return j + 1;
    }

    public static void main(final String[] arg) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {

            int k = Integer.parseInt(in.readLine());
            String[] nums = in.readLine().split("\\s+");
            int n = nums.length;

            BigInteger[] arr = new BigInteger[n];
            for (int i = 0; i < n; i++) arr[i] = new BigInteger(nums[i]);
            out.write(String.valueOf(findOrderStatistic(arr, k - 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}