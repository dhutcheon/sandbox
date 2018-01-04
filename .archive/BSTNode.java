package bst;

import collections.Tuple;

import java.util.Observable;
import java.util.Observer;

public class BSTNode<T extends Comparable<? super T>> extends Observable implements Observer {

    protected enum ChangeType {
        Parent,
        Left,
        Right,
        Level;
    }

    protected static class BSTNodeChangedEvent {
        private final ChangeType changeType;

        private final Object newVal;

        private BSTNodeChangedEvent(final Object newVal, final ChangeType changeType) {
            this.newVal = newVal;
            this.changeType = changeType;
        }

        public ChangeType getChangeType() {
            return changeType;
        }

        public Object getNewVal() { return newVal; }

        @Override
        public String toString() {
            return changeType + " " + ((newVal!=null) ? newVal.toString() : "null");
        }

    }

    private BSTNode<T> parent;

    private BSTNode<T> left;
    private BSTNode<T> right;
    private int x;
    private int y;

    private T val;

    protected BSTNode(BinarySearchTree<T> observer, BSTNode<T> parent, T val, int x, int y) {
        this.parent = parent;
        this.val = val;
        this.x = x;
        this.y = y;

        addObserver(observer);
    }

    protected BSTNode<T> getParent() {
        return parent;
    }

    protected void setParent(BSTNode<T> parent, boolean isLeftChild) {
        Tuple<Integer, Integer> coords = getChildCoordinates(parent, isLeftChild);
        this.x = coords.getX();
        this.y = coords.getY();

        if (this.parent != null)
            deleteObserver(this.parent);

        this.parent = parent;
        setChanged();
        notifyObservers(new BSTNodeChangedEvent(parent, ChangeType.Parent));

        if (this.parent != null)
            addObserver(this.parent);
    }

    protected BSTNode<T> getLeft() { return left; }

    protected void setLeft(BSTNode<T> left) {
        if (this.left == left)
            return;
        if (this.left != null)
            deleteObserver(this.left);

        this.left = left;
        setChanged();
        notifyObservers(new BSTNodeChangedEvent(left, ChangeType.Left));
        
        if (this.left != null)
            addObserver(this.left);
    }

    protected BSTNode<T> getRight() { return right; }

    protected void setRight(BSTNode<T> right) {
        if (this.right == right)
            return;
        if (this.right != null)
            deleteObserver(this.right);

        this.right = right;
        setChanged();
        notifyObservers(new BSTNodeChangedEvent(right, ChangeType.Right));

        if (this.right != null)
            addObserver(this.right);
    }

    protected void clearLinks() {
        setParent(null, parent.left == this);
        setLeft(null);
        setRight(null);
    }

    public T getVal() { return val; }


//  protected void setVal(T val) { this.val = val; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }

    @Override
    public String toString() {
        if (val == null)
            return super.toString();
        return val.toString();
    }

    @Override
    public void update(Observable o, Object arg) {
        //BSTNode<T> changed = (BSTNode)o;
        BSTNode.BSTNodeChangedEvent changedEvent = (BSTNode.BSTNodeChangedEvent) arg;
        Integer newLevel;

        switch (changedEvent.getChangeType()) {
            case Left:
                break;
            case Right:
                break;
            case Parent:
                newLevel = (parent == null) ? 0 : parent.y + 1;
                if (y == newLevel)
                    break;
                this.y = newLevel;
                notifyObservers(new BSTNodeChangedEvent(newLevel, ChangeType.Level));
                break;
            case Level:
                newLevel = (int)changedEvent.getNewVal();
                newLevel++;
                if (y == newLevel)
                    break;
                this.y = newLevel;
                notifyObservers(new BSTNodeChangedEvent(newLevel, ChangeType.Level));
                break;
        }
    }

    protected static Tuple<Integer,Integer> getChildCoordinates(BSTNode parent, boolean isLeftChild) {
        int x = 0;
        int y = 0;
        if (parent != null) {
            int parentX = parent.getX() + 1;
            int newMaxX = parentX * 2;
            x = (isLeftChild) ? newMaxX - 1 : newMaxX;
            x--;
            y = parent.y + 1;
        }
        return new Tuple<>(x, y);
    }
}
