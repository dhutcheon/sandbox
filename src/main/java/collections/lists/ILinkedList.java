package collections.lists;

import java.util.Iterator;

public interface ILinkedList<T extends Comparable<T>> extends Iterable<T> {

    void add(T val);

    void insert(T val, int index);

    T get(int index);

    T remove();

    T removeAt(int index);

    void reverse();

    void sort();

    int size();

    boolean isEmpty();


    @SuppressWarnings("unchecked")
    Iterator<T> iterator();

    void clear();
}
