package collections.lists;

public class DoubleLinkedNode<T extends Comparable<T>> extends Node<T> {

    DoubleLinkedNode<T> prev;
    DoubleLinkedNode<T> next;

    DoubleLinkedNode(T val) { super(val); }
}
