package collections.lists;

import java.util.Iterator;
import java.util.Stack;

public class LinkedList<T extends Comparable<T>> implements ILinkedList<T> { //implements ILinkedList<T> {

    //private final Class genericClass;

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public LinkedList() { } //this.genericClass = ReflectionUtils.getGenericClass(this.getClass()); }


    @Override
    public void add(T val) {
        //create new node
        Node<T> node = new Node<T>(val);
        //if the list is empty
        if (size == 0) {
            //set head new node
            this.head = node;
            //set tail as new node
            this.tail = node;
        } else if (size == 1) { //if the list is size 1
            //set the tail to the new node
            this.tail = node;
            //link head to new node
            this.head.next = node;
        } else { //if the list size > 1
            //link the old tail to the new node
            this.tail.next = node;
            //set the new node as tail
            this.tail = node;
        }

        //increment size
        size++;
    }




    @Override
    public void insert(T val, int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Insertion index (" + index + ") out of bounds");

        //if empty list or inserting at tail then we are appending to the list and can call add()
        if (isEmpty() || index == size) {
            add(val);
            return;
        }

        //create new node from val
        Node<T> node = new Node<>(val);

        //if inserting at head
        if (index == 0) {
            //get old head as next
            Node next = this.head;
            //set head from node
            this.head = node;
            //link node->next
            node.next = next;
        } else { //find the node at index specified
            Node<T> prev = getNodeAt(index-1);
            //save prev.next to temp
            Node temp = prev.next;
            //link prev->node
            prev.next = node;
            //link node->temp
            node.next = temp;
        }

        size++;

    }


    @Override
    public T get(int index) { return getNodeAt(index).getVal(); }

    private Node<T> getNodeAt(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException(index + " is out of bounds");

        Node<T> node = this.head;
        for (int i=0; i<size; i++) {
            if (i == index)
                return node;
            node = node.next;
        }

        throw new IllegalStateException("No node found at index " + index);
    }


    @Override
    public T remove() {
        if (isEmpty())
            return null;

        //Save tail val
        T val = tail.getVal();

        //if size is 1
        if (size == 1) {
            //set head to null
            this.head = null;
            //set tail to null
            this.tail = null;
        } else { //if size > 1
            Node<T> node = this.head;
            //iterate from head to tail until tail node is found
            while (node.next != this.tail)
                node = node.next;
            //set node to tail
            this.tail = node;
            //remove the link to the tail
            this.tail.next = null;
        }

        //decrement size
        size--;

        return val;
    }



    @Override
    public T removeAt(int index) {
        if (index < 0 || index > size-1)
            throw new IndexOutOfBoundsException("Insertion index (" + index + ") out of bounds");

        //if removing at tail can call remove()
        if (index == size-1)
            return remove();

        Node remove;
        if (index == 0) {
            //remove node is head
            remove = this.head;
            //new head is node after current head remove->next
            Node head = remove.next;

            //link new head
            this.head = head;
            //remove links on remove node
            remove.next = null;
        } else {
            Node prev = getNodeAt(index - 1);
            //remove node is prev->next
            remove = prev.next;
            //node after remove node is new orev->next
            Node next = remove.next;

            //link prev->next
            prev.next = next;
            //unlink remove
            remove.next = null;
        }

        size--;
        return (T) remove.getVal();
    }


    @Override
    public void reverse() {
        if (size < 2)
            return;

        //Create stack of T
        Stack<T> stack = new Stack<>();
        //Set pointer to head
        Node<T> n = head;
        //while pointer != null
        while (n != null) {
            //get val of pointer
            T val = n.getVal();
            //add val to stack
            stack.push(val);
            //set pointer to pointer.next
            n = n.next;
        }

        //set head null
        head = null;
        //set tail null
        tail = null;
        //set size to 0
        size = 0;

        //while the stack is not empty
        while (!stack.empty()) {
            T val = stack.pop();
            add(val);
        }
    }


    @Override
    public void sort() {
        if (this.size <= 1)
            return; // no need to sort any list with less than two nodes

        Node<T>[] nodes = toArrayInternal(); //create an array
        nodes = mergeSort(nodes); // do the sort

        Node<T> prev = null;
        for (int i=0; i<size; i++) {
            Node<T> n = nodes[i];
            if (i == 0) this.head = n;
            if (prev != null) prev.next = n;
            prev = n;
        }

        this.tail = prev; //set the last node as the new tail
        this.tail.next = null; //tail shouldn't have a next
    }

    private Node<T>[] mergeSort(Node<T>[] arr) {
        int n = arr.length;
        if (n == 1)
            return arr;

        int mid = n/2;
        int rLength = n - mid;
        Node<T>[] left = new Node[mid];
        Node<T>[] right = new Node[rLength];

        int i = 0, l = 0, r =0;
        while (i < mid) {
            left[l++] = arr[i++];
        }
        while (i < n) {
            right[r++] = arr[i++];
        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private Node<T>[] merge(Node<T>[] left, Node<T>[] right) {
        int lLength = left.length;
        int rLength = right.length;
        int length = lLength + rLength;
        Node<T>[] arr = new Node[length];

        int i = 0, r = 0, l = 0;
        while (i < length) {

            //if both left and right have elements to merge
            if (l < lLength && r < rLength) {
                T lVal = left[l].getVal();
                T rVal = right[r].getVal();

                //a[k++] = (a[j] < b[i]) ? a[j++] : b[i++]
                //if left is smaller, use left otherwise use right
                arr[i++] = (lVal.compareTo(rVal) < 0) ? left[l++] : right[r++];
            } else if (r >= rLength) { //if right side has no elements to merge, grab the rest of left
                arr[i++] = left[l++];
            } else if (l >= lLength) { //if left side has no elements to merge, grab the rest of right
                arr[i++] = right[r++];
            }
        }

        return arr;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }



    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        size = 0;
    }


//    public T[] toArray() {
//        T[] arr = (T[]) Array.newInstance(this.genericClass, this.size);
//        int i = 0;
//        while (this.iterator().hasNext()) {
//            arr[i] = this.iterator().next();
//            i++;
//        }
//        return arr;
//    }

    private Node<T>[] toArrayInternal() {
        Node<T>[] arr = new Node[this.size];
        Node<T> n = this.head;
        int i=0;
        while (n != null) {
            arr[i] = n;
            i++;
            n = n.next;
        }

        return arr;
    }



    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        this.forEach(t -> sb.append(t + ", "));
        sb.replace(sb.length()-2,sb.length(),"]");
        return sb.toString();
    }


    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {

        return new Iterator() {
            private Node<T> next = head;

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
}
