package ru.mail.polis.ads.task1.timatifey;

import java.io.*;
import java.util.EmptyStackException;

public class Problem6124 {
    public static void main(final String[] arg) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        Stack<Integer> stack = new Stack();
        String command;
        String answer;
        do {
            command = in.readLine();
            try {
                switch (command) {
                    case "pop": {
                        int res = stack.pop();
                        answer = String.valueOf(res);
                        break;
                    }
                    case "back": {
                        int res = stack.back();
                        answer = String.valueOf(res);
                        break;
                    }
                    case "size": {
                        answer = Integer.toString(stack.getSize());
                        break;
                    }
                    case "clear": {
                        stack.clear();
                        answer = "ok";
                        break;
                    }
                    case "exit": {
                        answer = "bye";
                        break;
                    }
                    default: {
                        int num = Integer.parseInt(command.split(" ")[1]);
                        stack.push(num);
                        answer = "ok";
                        break;
                    }
                }
            } catch (EmptyStackException err) {
                answer = "error";
            }
            out.write(answer + "\n");
        } while (!command.equals("exit"));
        out.close();
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
        this.last = new Node(element, currentLast);
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
