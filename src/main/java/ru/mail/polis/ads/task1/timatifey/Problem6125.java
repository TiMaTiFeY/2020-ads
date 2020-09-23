package ru.mail.polis.ads.task1.timatifey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Problem6125 {
    public static void main(final String[] arg) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        Queue<Integer> queue = new Queue();
        String command;
        String answer;
        do {
            command = in.readLine();
            try {
                switch (command) {
                    case "pop": {
                        int res = queue.pop();
                        answer = String.valueOf(res);
                        break;
                    }
                    case "front": {
                        int res = queue.front();
                        answer = String.valueOf(res);
                        break;
                    }
                    case "size": {
                        answer = Integer.toString(queue.getSize());
                        break;
                    }
                    case "clear": {
                        queue.clear();
                        answer = "ok";
                        break;
                    }
                    case "exit": {
                        answer = "bye";
                        break;
                    }
                    default: {
                        int num = Integer.parseInt(command.split(" ")[1]);
                        queue.push(num);
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
