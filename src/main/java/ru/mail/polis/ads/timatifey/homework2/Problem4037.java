package ru.mail.polis.ads.timatifey.homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Problem4037 {

    public static int[][] mergeSort(int[][] arr) {
        if (arr.length == 1) {
            return arr;
        }
        if (arr.length == 2) {
            return arr[0][0] <= arr[1][0] ? arr : new int[][] {arr[1], arr[0]};
        }
        int m = arr.length / 2;

        int[][] a = new int[m][2];
        for (int i = 0; i < m; i++) a[i] = arr[i];

        int[][] b = new int[arr.length - m][2];
        for (int i = 0; i < arr.length - m; i++) b[i] = arr[m + i];

        return merge(mergeSort(a), mergeSort(b));
    }

    public static int[][] merge(int[][] arrA, int[][] arrB) {
        int i = 0;
        int j = 0;
        int[][] result = new int[arrA.length + arrB.length][2];
        int k = 0;
        while (i < arrA.length || j < arrB.length) {
            if (i == arrA.length) {
                result[k++] = arrB[j++];
            } else if (j == arrB.length) {
                result[k++] = arrA[i++];
            } else result[k++] = arrA[i][0] <= arrB[j][0] ? arrA[i++] : arrB[j++];
        }
        return result;
    }

    public static void main(final String[] arg) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] s = in.readLine().split("\\s+");
            arr[i][0] = Integer.parseInt(s[0]);
            arr[i][1] = Integer.parseInt(s[1]);
        }
        arr = mergeSort(arr);
        for (int[] a: arr) out.write(a[0] + " " + a[1] + "\n");
        out.close();
    }

}
