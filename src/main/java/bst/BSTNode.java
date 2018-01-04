package bst;

import util.ReflectionUtils;


public class BSTNode<T extends Comparable<T>> implements Comparable<BSTNode<T>> {// extends Observable implements Observer {

    private T val;
    private int x = 0;
    private int y = 0;

    private BSTNode<T> parent;
    private BSTNode<T> left;
    private BSTNode<T> right;

    protected BSTNode(T val, BSTNode<T> parent) {
        this.val = val;
        this.parent = parent;
    }

    protected BSTNode<T> getParent() {
        return parent;
    }

    protected void setParent(BSTNode<T> parent) {
        this.parent = parent;
        //setCoordinates();

        if (parent == null) {
            this.x = 0;
            this.y = 0;
        } else {
            boolean isLeft = (this.parent.left == this);
            int parentX = parent.getX() + 1;
            int maxX = parentX * 2;
            this.x = ((isLeft) ? maxX - 1 : maxX) - 1;
            this.y = parent.getY() + 1;
        }
    }

    protected BSTNode<T> getLeft() {
        return left;
    }

    protected void setLeft(BSTNode<T> left) {
        this.left = left;
        if (left != null)
            left.setParent(this);
    }

    protected BSTNode<T> getRight() {
        return right;
    }

    protected void setRight(BSTNode<T> right) {
        this.right = right;
        if (right != null)
            right.setParent(this);
    }

    protected T getVal() {
        return val;
    }

    protected Integer getX() {
        return this.x;
    }

    protected Integer getY() {
        return this.y;
    }

    protected boolean isRoot() {
        return (this.parent == null);
    }

    @Override
    public String toString() {
        return (val == null) ? "VALUE NULL" : val.toString();
    }

//    private void setCoordinates() {
//        if (this.parent == null) {
//            this.x = 0;
//            this.y = 0;
//            return;
//        }
//        if (this.parent.left != this && this.parent.right != this)
//            throw new IllegalStateException("Could not determine L/R child from parent " + this.parent);
//        boolean isLeft = (this.parent.left == this);
//        Tuple<Integer, Integer> coords = getChildCoordinates(this.parent, isLeft);
//        this.x = coords.getX();
//        this.y = coords.getY();
//    }


//    public void clearLinks() {
//        this.left = null;
//        this.right = null;
//        this.parent = null;
//        this.x = 0;
//        this.y = 0;
//    }

    @Override
    public int compareTo(BSTNode<T> o) {
        if (o == null)
            return 1;
        T thisVal = getVal();
        T thatVal = o.getVal();
        return ReflectionUtils.compareTo(thisVal, thatVal);
    }
}
