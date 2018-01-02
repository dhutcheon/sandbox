package collections.lists;

import java.util.Iterator;


public interface ILinkedList<T extends Comparable<? super T>> extends Iterable<T> {
    boolean add(T val);

    T get(int index);

    T remove();

    void reverse();

    void insert(T val, int index);

    void sort();

    int size();

    boolean isEmpty();

    T[] toArray();

    @Override
    String toString();

    @Override
    Iterator<T> iterator();
}
