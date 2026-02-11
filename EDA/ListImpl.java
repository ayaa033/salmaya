package eda.ds;

import eda.adt.List;
import eda.exceptions.WrongIndexException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación dinámica de una lista enlazada simple.
 * @param <E> tipo de los elementos
 */
public class ListImpl<E> implements List<E> {

    private Node<E> head;
    private int size;

    /**
     * Clase interna Node
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public ListImpl() {
        head = null;
        size = 0;
    }

    @Override
    public void insert(int pos, E data) throws WrongIndexException {

        if (pos < 0 || pos > size)
            throw new WrongIndexException("Posición inválida");

        Node<E> newNode = new Node<>(data);

        if (pos == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<E> current = head;
            for (int i = 0; i < pos - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    @Override
    public void delete(int pos) throws WrongIndexException {

        if (pos < 0 || pos >= size)
            throw new WrongIndexException("Posición inválida");

        if (pos == 0) {
            head = head.next;
        } else {
            Node<E> current = head;
            for (int i = 0; i < pos - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }

        size--;
    }

    @Override
    public E get(int pos) throws WrongIndexException {

        if (pos < 0 || pos >= size)
            throw new WrongIndexException("Posición inválida");

        Node<E> current = head;

        for (int i = 0; i < pos; i++) {
            current = current.next;
        }

        return current.data;
    }

    @Override
    public int search(E data) {

        Node<E> current = head;
        int index = 0;

        while (current != null) {
            if ((data == null && current.data == null) ||
                (data != null && data.equals(current.data))) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Clase interna Iterator
     */
    private class CIterator implements Iterator<E> {

        private Node<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {

            if (!hasNext())
                throw new NoSuchElementException();

            E data = current.data;
            current = current.next;
            return data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new CIterator();
    }
}
