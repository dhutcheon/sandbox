package bst;

import collections.Tuple;


public class BSTNode2<T extends Comparable<T>> {// extends Observable implements Observer {


    private T val;
    private int x;
    private int y;

    private BSTNode2<T> parent;
    private BSTNode2<T> left;
    private BSTNode2<T> right;

    public BSTNode2(T val, BSTNode2<T> parent, int x, int y) {
        this.val = val;
        this.parent = parent;
        this.x = x;
        this.y = y;
    }

    public BSTNode2<T> getParent() {
        return parent;
    }

    public void setParent(BSTNode2<T> parent) {
        this.parent = parent;
        setCoordinates();
    }

    public BSTNode2<T> getLeft() {
        return left;
    }

    public void setLeft(BSTNode2<T> left) {
        this.left = left;
        if (left != null)
            left.setParent(this);
    }

    public BSTNode2<T> getRight() {
        return right;
    }

    public void setRight(BSTNode2<T> right) {
        this.right = right;
        if (right != null)
            right.setParent(this);
    }

    public T getVal() {
        return val;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    @Override
    public String toString() {
        return (val == null) ? "VALUE NULL" : val.toString();
    }

    private void setCoordinates() {
        if (this.parent == null) {
            this.x = 0;
            this.y = 0;
            return;
        }
        if (this.parent.left != this && this.parent.right != this)
            throw new IllegalStateException("Could not determine L/R child from parent " + this.parent);
        boolean isLeft = (this.parent.left == this);
        Tuple<Integer, Integer> coords = BinarySearchTree2.getChildCoordinates(this.parent, isLeft);
        this.x = coords.getX();
        this.y = coords.getY();
    }


    public void clearLinks() {
        this.left = null;
        this.right = null;
        this.parent = null;
    }

}
