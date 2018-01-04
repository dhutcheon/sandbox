package bst;


import collections.lists.ILinkedList;
import collections.lists.LinkedList;
import util.ReflectionUtils;


public class BinarySearchTree<T extends Comparable<T>> {

    //private final Class<?> genericClass;

    private BSTNode<T> root;

    private int size = 0;

    private T min;
    private T max;

    //private HashMap<Integer, Integer> rowWidth = new HashMap<>();

    public BinarySearchTree() { } //this.genericClass = ReflectionUtils.getGenericClass(this.getClass()); }
    public BinarySearchTree(T initialValue) { this(); insert(initialValue); }

    public void insert(T val) {
        if (val == null)
            throw new IllegalArgumentException("Insert value was null");

        insertInternal(val, this.root);

        size++;

        if (min == null || ReflectionUtils.lt(val, min))
            min = val;
        if (max == null || ReflectionUtils.gt(val, max))
            max = val;
    }

    private void insertInternal(T val, BSTNode<T> parent) {
        if (this.isEmpty()) {
            this.root = new BSTNode<>(val, null);
            return;
        }

        T parentVal = parent.getVal();
        int comparator = val.compareTo(parentVal);
        boolean lt;
        switch (comparator) {
            case 0:
                return;
            case -1:
            case 1:
                lt = (comparator == -1) ? true : false;
                break;
            default:
                throw new IllegalStateException("Comparator value " + comparator + " unhandled. Parent: " + parentVal + " Child: " + val);
        }

        BSTNode<T> selected = (lt) ? parent.getLeft() : parent.getRight();
        if (selected != null) {
            insertInternal(val, selected);
        } else {
            BSTNode<T> node = new BSTNode<>(val, parent);
            if (lt)
                parent.setLeft(node);
            else
                parent.setRight(node);
        }
    }

    public void reverse() { reverseInternal(this.root); }

    private void reverseInternal(BSTNode<T> parent) {
        if (parent == null)
            return;

        BSTNode<T> left = parent.getLeft();
        BSTNode<T> right = parent.getRight();
        parent.setLeft(right);
        parent.setRight(left);

        reverseInternal(left);
        reverseInternal(right);
    }

    public void balance() {
        /*     - 5 -
              /      \
             0        8
            / \      / \
          -1   3    6   9
                \    \   \
                 4    7   10
         */
        LinkedList<T> list = toLinkedList();
        list.sort();
        BinarySearchTree<T> tree = new BinarySearchTree<>();
        balanceInternal(tree, list, 0, list.size() - 1);
        this.root = tree.root;
    }

    private void balanceInternal(BinarySearchTree<T> tree, ILinkedList<T> list, int start, int end) {
        if (start > end)
            return;

        int mid = (start + end) / 2;
        T val = list.get(mid);
        tree.insert(val);

        balanceInternal(tree, list, start, mid - 1);
        balanceInternal(tree, list, mid + 1, end);
    }

    public void sort() {
        if (size < 3)
            return; //can't sort BST less than 3 nodes

        LinkedList<BSTNode<T>> list = toLinkedListOfNodes();
        list.sort();

        //find the middle node in the list
        int index = (size-1) / 2;
        int mid = index;
        T rootVal = list.get(mid).getVal();
        BSTNode<T> newRoot = new BSTNode<>(rootVal, null);

        //assign it to root
        this.root = newRoot;

        BSTNode<T> parent;

        //right side of the list from middle (root) becomes right branches off of middle
        //set index to middle + 1
        index = mid + 1;

        //track the parent node
        parent = newRoot;

        //while index < size
        while (index < size) {
            //get the node at index
            BSTNode<T> n = list.get(index);

            //clear any current links
            n.setLeft(null);
            n.setRight(null);
            n.setParent(null);

            //add the node as the right child of its parent
            parent.setRight(n);

            //increment index and set parent for next node
            index++;
            parent = n;
        }

        //left side of the list from middle (root) becomes left branches off of middle
        //set index to middle - 1
        index = mid - 1;

        //track the parent node
        parent = newRoot;

        //while index is not at beginning of list
        while (index >= 0) {
            //get the node at index
            BSTNode<T> n = list.get(index);

            //clear any current links
            n.setLeft(null);
            n.setRight(null);
            n.setParent(null);

            //add the node as the left child of its parent
            parent.setLeft(n);

            //decrement index and set parent for next node
            index--;
            parent = n;
        }
    }

    public boolean exists(T val) {
        return existsInternal(val, this.root);
    }

    private boolean existsInternal(T val, BSTNode<T> parent) {
        if (parent == null)
            return false;

        T parentVal = parent.getVal();
        int comparator = val.compareTo(parentVal);
        switch (comparator) {
            case 0:
                return true;
            case -1:
                return existsInternal(val, parent.getLeft());
            case 1:
                return existsInternal(val, parent.getRight());
        }

        return false;
    }

    public boolean isEmpty() { return (this.root == null); }

    public T getMin() { return min; }

    public T getMax() {
        return max;
    }

    public int getSize() { return size; }

    public int getHeight() { return getHeightInternal(root); }

    private int getHeightInternal(BSTNode<T> node) {
        if (node == null) return -1;

        int l = getHeightInternal(node.getLeft());
        int r = getHeightInternal(node.getRight());
        int height = node.getY() + 1;

        if (height > l && height > r) return height;
        return (r > l) ? r : l;
    }

    public LinkedList<T> toLinkedList() {
        LinkedList<T> list = new LinkedList<T>();
        buildListInternal(this.root, list, false);
        return list;
    }

    private LinkedList<BSTNode<T>> toLinkedListOfNodes() {
        LinkedList<BSTNode<T>> list = new LinkedList<>();
        buildListInternal(this.root, list, true);
        return list;
    }

    private void buildListInternal(BSTNode<T> node, LinkedList list, boolean listOfNodes) {
        if (node == null)
            return;
        buildListInternal(node.getLeft(), list, listOfNodes);
        list.add((listOfNodes) ? node : node.getVal());
        buildListInternal(node.getRight(), list, listOfNodes);
    }

    public void clear() {
        clearInternal(this.root);
        this.size = 0;
        this.root = null;
    }

    private void clearInternal(BSTNode<T> node) {
        if (node == null)
            return;

        //node.deleteObservers();
        clearInternal(node.getLeft());
        clearInternal(node.getRight());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        BSTNode<T>[][] array = toJaggedArray();

        for (int y=0; y<array.length; y++) {
            for (int x=0; x<array[y].length; x++) {
                BSTNode<T> node = array[y][x];
                String str = (node == null) ? "  " : node.getVal().toString();
                if (str.length() == 1)
                    str = " " + str;
                sb.append(str);
                sb.append("|");
            }
            sb.append("\r\n");
        }

        return sb.toString();
    }

    private BSTNode<T>[][] toJaggedArray() {
        int height = getHeight();
        BSTNode<T>[][] arr = new BSTNode[height][];
        toJaggedArrayInternal(this.root, arr);
        return arr;
    }

    private void toJaggedArrayInternal(BSTNode<T> parent, BSTNode<T>[][] arr) {
        if (parent == null)
            return;

        int x = parent.getX();
        int y = parent.getY();
        if (arr[y] == null) {
            int cols = (int) Math.pow(2, y);
            arr[y] = new BSTNode[cols];
        }

        arr[y][x] = parent;

        toJaggedArrayInternal(parent.getLeft(), arr);
        toJaggedArrayInternal(parent.getRight(), arr);
    }
}
