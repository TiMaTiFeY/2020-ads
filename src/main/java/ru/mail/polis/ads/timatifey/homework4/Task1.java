package ru.mail.polis.ads.timatifey.homework4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
Task text: https://www.e-olymp.com/ru/problems/1087
Task tests: https://www.e-olymp.com/ru/submissions/7542560
 */

public class Task1 {
    public static boolean areCorrectChars(char a, char b) {
        return (a == '(' && b == ')') || (a == '[' && b == ']');
    }

    public static CorrectBracket getBracket(int offset, int index, char bracket) {
        boolean addRight = true;
        char newChar = '_';
        switch(bracket) {
            case '(': {
                newChar = ')';
                break;
            }
            case ')': {
                newChar = '(';
                addRight = false;
                break;
            }
            case '[': {
                newChar = ']';
                break;
            }
            case ']': {
                newChar = '[';
                addRight = false;
                break;
            }
        }
        return new CorrectBracket(index + offset + (addRight ? 1 : 0), newChar);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            String s = in.nextLine();
            if (s == null || s.equals("")) {
                out.write("");
                return;
            }
            int n = s.length();
            int[][] d = new int[n][n];
            int[][] path = new int[n][n];

            //Подсчет минимального количества скобок
            for (int m = 0; m < n; m++) {
                int j = m, i = 0;
                while (j != n) {
                    //База динамики
                    if (i == j) {
                        d[i][j] = 1;
                        path[i][j] = -1;
                    } else {
                        //Разбиение
                        int minSum = d[i][i] + d[i + 1][j];
                        int bestK = i;
                        for (int t = i + 1; t < j; t++) {
                            if (d[i][t] + d[t + 1][j] < minSum) {
                                minSum = d[i][t] + d[t + 1][j];
                                bestK = t;
                            }
                        }

                        if (areCorrectChars(s.charAt(i), s.charAt(j)) && d[i + 1][j - 1] < minSum) {
                            d[i][j] = d[i + 1][j - 1];
                            path[i][j] = -2;
                        } else {
                            d[i][j] = minSum;
                            path[i][j] = bestK;
                        }
                    }
                    i++;
                    j++;
                }
            }

//            printArray(d, s);
//            printArray(path, s);

            //Поиск нужных скобок для дополнения
            boolean[] needAdd = new boolean[n];
            Queue<Cell> queue = new LinkedList();
            queue.add(new Cell(0, n - 1, path[0][n - 1]));
            while (queue.size() != 0) {
                Cell curr = queue.poll();
                if (curr.i > curr.j) continue;
                if (curr.k != -1) {
                    if (curr.k == -2)
                        queue.add(new Cell(curr.i + 1, curr.j - 1, path[curr.i + 1][curr.j - 1]));
                    else {
                        queue.add(new Cell(curr.i, curr.k, path[curr.i][curr.k]));
                        queue.add(new Cell(curr.k + 1, curr.j, path[curr.k + 1][curr.j]));
                    }
                } else {
                    needAdd[curr.i] = true;
                }
            }

//            System.out.println(Arrays.toString(needAdd));

            //Формирование результата
            StringBuilder sb = new StringBuilder(s);
            int offset = 0;
            for (int i = 0; i < n; i++) {
                if (needAdd[i]) {
                    CorrectBracket correctBracket = getBracket(offset, i, s.charAt(i));
                    sb.insert(correctBracket.index, correctBracket.bracket);
                    offset++;
                }
            }
            out.write(sb.toString());
        }
    }

    public static class Cell {
        int i;
        int j;
        int k;
        Cell(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }
    }

    public static class CorrectBracket {
        int index;
        char bracket;
        CorrectBracket(int index, char bracket) {
            this.index = index;
            this.bracket = bracket;
        }
    }
}