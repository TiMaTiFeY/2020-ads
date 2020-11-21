package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Table<Key extends Comparable<Key>, Value>
        implements HashTable<Key, Value> {

    private static class Cell<Key extends Comparable<Key>, Value> {
        private final Key key;
        private Value value;
        private Cell<Key, Value> next;

        Cell(Key key, Value value, Cell<Key, Value> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private static final float FILL_FACTOR = (float) 1.5;

    private int size = 0;
    private int capacity = 16;

    private Cell<Key, Value>[] table = new Cell[capacity];

    private int index(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize() {
        capacity *= 2;
        Cell[] oldTable = table;
        table = new Cell[capacity];
        size = 0;
        for (Cell<Key, Value> cell : oldTable) {
            Cell<Key, Value> current = cell;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int index = index(key);
        Cell<Key, Value> current = table[index];
        while (current != null && !current.key.equals(key)) {
            current = current.next;
        }
        return current != null ? current.value : null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int index = index(key);
        Cell<Key, Value> current = table[index];
        if (current == null) {
            //Если пустая ячейка
            table[index] = new Cell(key, value, null);
            size++;
        } else {
            while (current.next != null && !current.next.key.equals(key)) {
                current = current.next;
            }
            if (current.key.equals(key)) {
                current.value = value;
            } else {
                if (current.next == null) {
                    //Если не пустая ячейка, добавляем в конец списка
                    current.next = new Cell(key, value, null);
                    size++;
                } else {
                    //Меняем значение, если ключ уже существует
                    current.next.value = value;
                }
            }
        }
        if (size >= FILL_FACTOR * capacity) {
            resize();
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int index = index(key);
        Cell<Key, Value> current = table[index];
        if (current == null) {
            return null;
        }
        if (current.key.equals(key)) {
            Value removeValue = current.value;
            table[index] = current.next;
            size--;
            return removeValue;
        }
        while (current.next != null && !current.next.key.equals(key)) {
            current = current.next;
        }
        //Если дошли до конца списка и не нашли нужный ключ
        if (current.next == null) {
            return null;
        }
        Value removeValue = current.next.value;
        current.next = current.next.next;
        size--;
        return removeValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
