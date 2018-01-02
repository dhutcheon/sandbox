package bst;


import collections.Tuple;
import collections.lists.LinkedList;
import util.ReflectionUtils;

import java.util.Vector;


public class BinarySearchTree2 <T extends Comparable<T>> {

    //private final Class<?> genericClass;

    private BSTNode2<T> root;

    private int size = 0;

    private T min;
    private T max;

    //private HashMap<Integer, Integer> rowWidth = new HashMap<>();

    public BinarySearchTree2() { } //this.genericClass = ReflectionUtils.getGenericClass(this.getClass()); }
    public BinarySearchTree2(T initialValue) { this(); insert(initialValue); }

    public void insert(T val) {
        if (val == null)
            throw new IllegalArgumentException("Insert value was null");

        insertInternal(val, this.root);

        size++;

        if (ReflectionUtils.lt(val, min))
            min = val;
        if (ReflectionUtils.gt(val, max))
            max = val;
    }

    private void insertInternal(T val, BSTNode2<T> parent) {
        if (this.isEmpty()) {
            this.root = new BSTNode2<>(val, null, 0, 0);
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

        BSTNode2<T> selected = (lt) ? parent.getLeft() : parent.getRight();
        if (selected != null) {
            insertInternal(val, selected);
        } else {
            Tuple<Integer, Integer> coords = getChildCoordinates(parent, lt);
            BSTNode2<T> node = new BSTNode2<>(val, parent, coords.getX(), coords.getY());
            if (lt)
                parent.setLeft(node);
            else
                parent.setRight(node);
        }
    }

    public void reverse() { reverseInternal(this.root); }

    private void reverseInternal(BSTNode2<T> parent) {
        if (parent == null)
            return;

        BSTNode2<T> left = parent.getLeft();
        BSTNode2<T> right = parent.getRight();
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
        //LinkedList<BSTNode2<T>> list = toLinkedListOfNodes();
        //this.root = balanceInternal(list,null,0, list.size()-1);
    }

//    private BSTNode2<T> balanceInternal(LinkedList<BSTNode2<T>> list, BSTNode2<T> parent, int start, int end) {
//        // base case
//        if (start > end)
//            return null;
//
//        /* Get the middle element and make it root */
//        int mid = (start + end) / 2;
//        BSTNode2<T> node = list.get(mid);
//        node.clearLinks();
//
//        /* Using index in Inorder traversal, construct
//           left and right subtress */
//        BSTNode2<T> left = balanceInternal(list, node, start, mid - 1);
//        if (left != null) {
//            //left.clearLinks();
//            left.setParent(node);
//            node.setLeft(left);
//        }
//
//        BSTNode2<T> right = balanceInternal(list, node,mid + 1, end);
//        if (right != null) {
//            //right.clearLinks();
//            right.setParent(node);
//            node.setRight(right);
//        }
//
//        return node;
//    }

    public void sort() {

    }

    public boolean exists(T val) {
        return existsInternal(val, this.root);
    }

    private boolean existsInternal(T val, BSTNode2<T> parent) {
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

    private int getHeightInternal(BSTNode2<T> node) {
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

//    private LinkedList<BSTNode2<T>> toLinkedListOfNodes() {
//        LinkedList<BSTNode2<T>> list = new LinkedList<>();
//        buildListInternal(this.root, list, true);
//        return list;
//    }

    private void buildListInternal(BSTNode2<T> node, LinkedList list, boolean listOfNodes) {
        if (node == null)
            return;
        buildListInternal(node.getLeft(), list, listOfNodes);
        list.add(node.getVal());
        buildListInternal(node.getRight(), list, listOfNodes);
    }

    public void clear() {
        clearInternal(this.root);
        this.size = 0;
        this.root = null;
    }

    private void clearInternal(BSTNode2<T> node) {
        if (node == null)
            return;

        //node.deleteObservers();
        clearInternal(node.getLeft());
        clearInternal(node.getRight());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        BSTNode2<T>[][] array = toJaggedArray();

        for (int y=0; y<array.length; y++) {
            for (int x=0; x<array[y].length; x++) {
                BSTNode2<T> node = array[y][x];
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

    private BSTNode2<T>[][] toJaggedArray() {
        int height = getHeight();
        BSTNode2<T>[][] arr = new BSTNode2[height][];
        toJaggedArrayInternal(this.root, arr);
        return arr;
    }

    private void toJaggedArrayInternal(BSTNode2<T> parent, BSTNode2<T>[][] arr) {
        if (parent == null)
            return;

        int x = parent.getX();
        int y = parent.getY();
        if (arr[y] == null) {
            int cols = (int) Math.pow(2, y);
            arr[y] = new BSTNode2[cols];
        }

        arr[y][x] = parent;

        toJaggedArrayInternal(parent.getLeft(), arr);
        toJaggedArrayInternal(parent.getRight(), arr);
    }



    protected static Tuple<Integer,Integer> getChildCoordinates(BSTNode2 parent, boolean isLeftChild) {
        int parentX = parent.getX() + 1;
        int newMaxX = parentX * 2;
        int x = ((isLeftChild) ? newMaxX - 1 : newMaxX) - 1;
        int y = parent.getY() + 1;
        return new Tuple<>(x, y);
    }


}
