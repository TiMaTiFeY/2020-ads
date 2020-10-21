package ru.mail.polis.ads.timatifey.homework5;

import java.util.Scanner;

/*
Task text: https://www.e-olymp.com/ru/problems/991
Task tests: https://www.e-olymp.com/ru/submissions/7558509
 */

public class Task5 {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        boolean firstLineIsWord = true;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == '*' || s1.charAt(i) == '?') {
                firstLineIsWord = false;
                break;
            }
        }
        String word;
        String template;
        if (firstLineIsWord) {
            word = s1;
            template = s2;
        } else {
            word = s2;
            template = s1;
        }
        boolean[][] d = new boolean[template.length() + 1][word.length() + 1];
        //База динамики
        d[0][0] = true;
        for (int i = 1; i <= template.length(); i++)
            if (template.charAt(i - 1) == '*') d[i][0] = true;
            else break;
        for (int i = 1; i <= template.length(); i++)
            for (int j = 1; j <= word.length(); j++) {
                switch (template.charAt(i - 1)) {
                    case '?': {
                        d[i][j] = d[i - 1][j - 1];
                        break;
                    }
                    case '*': {
                        d[i][j] = d[i - 1][j - 1] || d[i][j - 1] || d[i - 1][j];
                        break;
                    }
                    default: {
                        d[i][j] = template.charAt(i - 1) == word.charAt(j - 1) && d[i - 1][j - 1];
                        break;
                    }
                }
            }
        System.out.println(d[template.length()][word.length()] ? "YES" : "NO");
    }
}
