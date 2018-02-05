package collections.lists;

public class LinkedListNode<T extends Comparable<T>> extends Node<T> {
    LinkedListNode<T> next;

    LinkedListNode(T val) { super(val); }
}
