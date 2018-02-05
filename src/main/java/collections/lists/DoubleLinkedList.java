package collections.lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DoubleLinkedList<T extends Comparable<T>> implements ILinkedList<T> {

    private final Class<T> genericClass;
    private int size;

    private DoubleLinkedNode<T> head;
    private DoubleLinkedNode<T> tail;

    public DoubleLinkedList(Class<T> cls) { this.genericClass = cls; }
    public DoubleLinkedList(Class<T> cls, T initialVal) { this.genericClass = cls; add(initialVal); }
    public DoubleLinkedList(Class<T> cls, Collection<T> initialValues) { this.genericClass = cls; initialValues.stream().forEach(v -> add(v)); }


    private DoubleLinkedNode<T> getNodeAt(int index) {
        if (size == 0) return null;
        if (size == 1) return this.head;
        if (index == (size-1)) return this.tail;

        int mid = size / 2;
        if (index > mid) {
            //work from the tail
            int i = size - 1;
            DoubleLinkedNode<T> n = this.tail;
            while (i > index) {
                n = n.prev;
                i--;
            }
            return n;
        } else {
            //work from the head
            int i = 0;
            DoubleLinkedNode<T> n = this.head;
            while (i < index) {
                n = n.next;
                i++;
            }
            return n;
        }
    }

    @Override
    public void add(T val) {
        DoubleLinkedNode<T> next = new DoubleLinkedNode<>(val);
        if (size == 0) {
            head = next;
            tail = next;
            size++;
            return;
        }

        DoubleLinkedNode prev = tail;
        prev.next = next;
        next.prev = prev;
        tail = next;

        size++;
    }

    @Override
    public void insert(T val, int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index " + index + " is not within bounds of list with size " + size);

        //if empty list or adding at end of list, use default add() logic
        if (size == 0 || index == size) {
            add(val);
            return;
        }

        DoubleLinkedNode<T> insert = new DoubleLinkedNode<>(val);
        DoubleLinkedNode<T> next = getNodeAt(index);
        DoubleLinkedNode<T> prev = next.prev;

        insert.next = next;
        insert.prev = prev;
        if (prev != null)
            prev.next = insert;
        if (next != null)
            next.prev = insert;
        if (index == 0)
            this.head = insert;
        if (index == size)
            this.tail = insert;

        size++;
    }

    @Override
    public T get(int index) {
        DoubleLinkedNode<T> n = getNodeAt(index);
        return (n == null) ? null : n.getVal();
    }


    @Override
    public T remove() {
        if (size == 0)
            return null;

        T val = this.tail.getVal();

        this.tail = this.tail.prev;
        if (size == 1) //if we are removing the last element it is also the head
            this.head = null;

        size--;
        return val;
    }

    @Override
    public T removeAt(int index) {
        //if empty list or removing at end of list, use default remove() logic
        if (size == 0 || index == (size-1))
            return remove();

        DoubleLinkedNode<T> remove = getNodeAt(index);
        DoubleLinkedNode<T> prev = remove.prev;
        DoubleLinkedNode<T> next = remove.next;
        if (prev != null)
            prev.next = next;
        if (next != null)
            next.prev = prev;
        if (index == 0)
            this.head = next;
        if (index == size)
            this.tail = prev;

        size--;
        return remove.getVal();
    }

    @Override
    public void reverse() {

    }

    @Override
    public void sort() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator() {
            private DoubleLinkedNode<T> next = head;

            @Override
            public boolean hasNext() {
                return (next != null);
            }

            @Override
            public T next() {
                if (next == null)
                    return null;

                //save next value as return value
                T retVal = next.getVal();
                //set next.next as next
                next = next.next;
                //return value
                return retVal;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (T t : this) {
            action.accept(t);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public T[] toArray() {
        return null;
    }

    public T getHead() { return (isEmpty()) ? null : this.head.getVal(); }

    public T getTail() { return (isEmpty()) ? null : this.tail.getVal(); }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";

        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T val = iterator.next();
            sb.append(val);

            if (i != size-1)
                sb.append(", ");
            i++;
        }

        sb.append("]");
        return sb.toString();
    }
}
