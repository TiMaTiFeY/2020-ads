package ru.mail.polis.ads.timatifey.homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Problem3837 {
    public static void main(final String[] arg) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int count = Integer.parseInt(in.readLine());

        for (int i = 0; i < count; i++) {
            String commands = in.readLine();
            Stack<Expression> stack = new Stack();

            for (char ch : commands.toCharArray()) {
                String el = String.valueOf(ch);
                if (el.equals(el.toLowerCase())) {
                    stack.push(new Expression.Constant(ch));
                } else {
                    Expression a = stack.pop();
                    Expression b = stack.pop();
                    stack.push(new Expression.Binary(b, a, ch));
                }
            }
            Expression.Binary exp = (Expression.Binary) stack.pop();

            Queue<Expression> queue = new Queue();
            queue.push(exp);
            StringBuilder res = new StringBuilder(exp.getOp());

            while (queue.getSize() != 0) {
                Expression currentExp = queue.pop();
                if (isBinary(currentExp)) {
                    Expression.Binary currBin = (Expression.Binary) currentExp;

                    if (isBinary(currBin.getLeft())) {
                        res.append(((Expression.Binary) currBin.getLeft()).getOp());
                        queue.push(currBin.getLeft());
                    } else {
                        res.append(((Expression.Constant) currBin.getLeft()).getValue());
                    }

                    if (isBinary(currBin.getRight())) {
                        res.append(((Expression.Binary) currBin.getRight()).getOp());
                        queue.push(currBin.getRight());
                    } else {
                        res.append(((Expression.Constant) currBin.getRight()).getValue());
                    }
                } else {
                    res.append(((Expression.Constant) currentExp).getValue());
                }
            }
            out.write(res.reverse().toString() + "\n");
        }
        out.close();
    }

    public static boolean isBinary(Expression exp) {
        return exp.getClass().equals(Expression.Binary.class);
    }
}

class Expression {
    static class Constant extends Expression {
        final private char value;
        Constant(char value) {
            this.value = value;
        }

        public String getValue() {
            return String.valueOf(value);
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    static class Binary extends Expression {
        final private Expression left;
        final private Expression right;
        final private char op;

        Binary(Expression left, Expression right, char op) {
            this.left = left;
            this.right = right;
            this.op = op;
        }

        public Expression getLeft() {
            return left;
        }

        public Expression getRight() {
            return right;
        }

        public String getOp() {
            return String.valueOf(op);
        }

        @Override
        public String toString() {
            return "(" + left + " " + op + " " + right + ")";
        }
    }
}
