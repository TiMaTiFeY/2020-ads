package ru.mail.polis.ads.timatifey.homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Problem4827 {

    /*static void kStat(BigInteger[] array, int l, int r, int k) {
        int i = l;
        int j = r;
        BigInteger x = array[(l + r) / 2];
        while (i <= j) {
            while (array[i].compareTo(x) < 0)
                i++;
            while (array[j].compareTo(x) > 0)
                j--;
            if (i > j)
                break;
            BigInteger t = array[i];
            array[i] = array[j];
            array[j] = t;
            i++;
            j--;
        }
        System.out.println(Arrays.toString(array));
        if (k <= j) {
            kStat(array, l, j, k);
        }
        else if (k >= i) {
            kStat(array, i, r, k);
        }
    }*/

    public static void kStat(BigInteger[] arr, int left, int right, int mid, int n) {
        int l = left;
        int r = right;
        BigInteger basic = arr[mid];

        while (l <= r) {
            while (arr[l].compareTo(basic) < 0) {
                ++l;
            }
            while (arr[r].compareTo(basic) > 0) {
                --r;
            }
            if (l <= r) {
                BigInteger b = arr[r];
                arr[r] = arr[l];
                arr[l] = b;
                --r;
                ++l;
            }
        }
        if (n <= r) {
            right = r;
            kStat(arr, left, right, (right - left) / 2 + left, n);
        } else if (n >= l) {
            left = l;
            kStat(arr, left, right, (right - left) / 2 + left, n);
        }
    }

    public static void main(final String[] arg) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            try (PrintWriter out = new PrintWriter(System.out)) {

            int k = Integer.parseInt(in.readLine());
            String[] nums = in.readLine().split("\\s+");
            int n = nums.length;

            BigInteger[] arr = new BigInteger[n];
            for (int i = 0; i < n; i++) arr[i] = new BigInteger(nums[i]);
            //1, 2, 3, 4, 5, 6, 2, 5, 9, 10
            kStat(arr, 0, arg.length - 1, arr.length / 2, arg.length - k);
            out.write(String.valueOf(arr[arr.length - k]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
