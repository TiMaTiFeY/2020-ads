package ru.mail.polis.ads.task1.timatifey;

import java.io.*;
import java.util.EmptyStackException;

public class Problem3837 {
    public static void main(final String[] arg) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int count = Integer.parseInt(in.readLine());

        for (int i = 0; i < count; i++) {
            String commands = in.readLine();
            Stack<Expression> stack = new Stack();

            //Создаем дерево для этого выражения
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

            //Используя принцип поиска в ширину дерево обратно преобразовываем в строку
            //Принцип таков, что собрать все символы с каждого уровня уровня дерева слева направо
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

class Stack<E>{
    private int size;
    private Node<E> last;

    Stack() {
        this.size = 0;
        this.last = null;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public void push(E element){
        Node<E> currentLast = this.last;
        this.last = new Node(element, currentLast);;
        size++;
    }

    public E pop() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();
        Node<E> currentLast = this.last;
        this.last = this.last.next;
        size--;
        return currentLast.item;
    }

    public E back() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();
        return this.last.item;
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        Node next;
        for(Node x = this.last; x != null; x = next) {
            next = x.next;
            x.item = null;
            x.next = null;
        }
        this.size = 0;
        this.last = null;
    }
}

class Queue<E> {
    private int size;
    private Node<E> last;
    private Node<E> first;

    Queue() {
        this.size = 0;
        this.last = null;
        this.first = null;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void push(E element){
        if (size == 0) {
            this.first = this.last = new Node(null, element, null);
        } else {
            Node<E> currentLast = this.last;
            Node<E> newLast = new Node(null, element, currentLast);
            this.last = newLast;
            currentLast.prev = newLast;
        }
        size++;
    }

    public E pop() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();
        Node<E> currentFirst = this.first;
        this.first = this.first.prev;
        size--;
        return currentFirst.item;
    }

    public E front() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();
        return this.first.item;
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        Node next;
        for(Node x = this.last; x != null; x = next) {
            next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
        }
        this.size = 0;
        this.last = null;
        this.first = null;
    }
}
