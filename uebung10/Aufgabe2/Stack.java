package org.hbrs.se1.ws22.uebung10.Aufgabe2;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack<T> {
    private Object[] array = new Object[MAX];
    private int size = 0;
    private String zustand = "empty";
    private static final int MAX = 4;

    public Stack() {
    }

    public T push(T item) {
        if (size < MAX-1) {
            zustand = "filled";
        }
        if (size == MAX-1) {
            zustand = "full";
        }
        if (size == MAX) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 0) {
            array[0] = item;
            size++;
        } else {
            array[size++] = item;
        }
        return item;
    }

    public synchronized T pop() throws EmptyStackException {
        if (empty()) {
            throw new EmptyStackException();
        }

        if (size == 1) {
            zustand = "empty";
        }

        if (size > 1) {
            zustand = "filled";
        }

        size--;
        T item = (T) array[size];
        array[size] = null;
        return item;
    }

    public T peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return (T) array[0];
    }

    public boolean empty() {
        return size == 0;
    }

    public String getZustand() {
        return zustand;
    }

    public int getSize() {
        return size;
    }
}
