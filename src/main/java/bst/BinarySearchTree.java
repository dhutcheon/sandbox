package bst;


import collections.Tuple;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class BinarySearchTree <T extends Comparable<? super T>> implements Observer {

    private final Class<T> genericClass;

    private BSTNode<T> root;

    //private int height = 0;
    //private int width = 0;
    private int size = 0;

    private T min;
    private T max;

    //private HashMap<Integer, Integer> rowWidth = new HashMap<>();

    public BinarySearchTree(Class<T> cls) { this.genericClass = cls; }
    public BinarySearchTree(Class<T> cls, T initialValue) { this(cls); insert(initialValue); }

    public void insert(T val) {
        if (val == null)
            throw new IllegalArgumentException("Insert value was null");

        //if no root, set it
        if (root == null) {
            root = new BSTNode<>(this,null, val, 0, 0);
            size++;
            //height = 1;
            //width = 1;
            //rowWidth.put(height, 1);
            return;
        }

        if (eq(root.getVal(),val))
            return;//insert value and root value are same so do nothing

        //insert recursively
        BSTNode<T> node = insertInternal(val, root);
        if (node == null)
            return;

        size++;
//        if (node.getLevel() + 1 > height)
//            height = node.getLevel() + 1;
        if (lt(val, min))
            min = val;
        if (gt(val, max))
            max = val;
    }

    private BSTNode<T> insertInternal(T val, BSTNode<T> parent) {
//        if (parent == null)
//            throw new IllegalStateException("Parent node was null when inserting value " + val);
        T parentVal = parent.getVal();
        int comparator = val.compareTo(parentVal);
        if (comparator == 0)
            return null; //insert value and current value are same so we're done

        BSTNode<T> selectedNode = (comparator == -1) ? parent.getLeft() : parent.getRight();
        if (selectedNode == null) {
            boolean left = (comparator == -1);
            Tuple<Integer, Integer> coords = BSTNode.getChildCoordinates(parent, left);
            int x = coords.getX();
            int y = coords.getY();
            selectedNode = new BSTNode<>(this, parent, val, x, y);
            if (left)
                parent.setLeft(selectedNode);
            else
                parent.setRight(selectedNode);

            return selectedNode;
        } else {
            return insertInternal(val, selectedNode);
        }
    }

    public boolean remove(T val) {
        /*     - 5 -
              /      \
             0        8
            / \      / \
          -1   3    6   9
                \    \
                 4    7
         */
        boolean success = removeInternal(val, this.root);
        if (success)
            size--;
        return success;
    }

    private boolean removeInternal(T val, BSTNode<T> node) {
        if (node == null)
            return false;
        if (!eq(val, node.getVal())) {
            if (gt(val,node.getVal())) {
                return removeInternal(val, node.getRight());
            } else {
                return removeInternal(val, node.getLeft());
            }
        }

        BSTNode<T> parent = node.getParent();
        BSTNode<T> left = node.getLeft();
        BSTNode<T> right = node.getRight();

        //if parent null
        if (parent == null)
            //at root, throw exception for now
            throw new IllegalArgumentException("Cannot remove root");

        //delete observers
        node.deleteObservers();
        //remove the link from the parent to the node that is being removed
        node.setParent(null, (parent.getLeft() == node));
        //remove link from removed node to parent
        if (parent.getLeft() == node)
            parent.setLeft(null);
        if (parent.getRight() == node)
            parent.setRight(null);

        //if left and right null we are done
        if (left == null && right == null)
            return true;

        //grab right node to replace deleted if it exists
        //otherwise grab left
        BSTNode<T> selected = (right != null) ? right : left;

        //remove the left and right links to the removed node
        node.setRight(null);
        node.setLeft(null);

        //remove links from L,R to removed node
        left.setParent(null, true);
        right.setParent(null, false);

        //link parent to selected node
        selected.setParent(parent, (right == null));

        //link selected node to parent
        parent.setRight(selected);

        //if selected node is right node, link left to selected node
        if (selected == right) {
            selected.setLeft(left);
            left.setParent(selected, true);
        }

        //if selected node is left node, no need to link anything but parent

        //any right links to selected node remain

        return true;
    }


//    public void balance() {
//        /*     - 5 -
//              /      \
//             0        8
//            / \      / \
//          -1   3    6   9
//                \    \   \
//                 4    7   10
//         */
//        LinkedList<BSTNode<T>> list = toLinkedListOfNodes();
//        this.root = balanceInternal(list,null,0, list.size()-1);
//    }

//    private BSTNode<T> balanceInternal(LinkedList<BSTNode<T>> list, BSTNode<T> parent, int start, int end) {
//        // base case
//        if (start > end)
//            return null;
//
//        /* Get the middle element and make it root */
//        int mid = (start + end) / 2;
//        BSTNode<T> node = list.get(mid);
//        node.clearLinks();
//
//        /* Using index in Inorder traversal, construct
//           left and right subtress */
//        BSTNode<T> left = balanceInternal(list, node, start, mid - 1);
//        if (left != null) {
//            //left.clearLinks();
//            left.setParent(node, true);
//            node.setLeft(left);
//        }
//
//        BSTNode<T> right = balanceInternal(list, node,mid + 1, end);
//        if (right != null) {
//            //right.clearLinks();
//            right.setParent(node, false);
//            node.setRight(right);
//        }
//
//        return node;
//    }



//    public LinkedList<T> toLinkedList() {
//        LinkedList<T> list = new LinkedList<>();
//        buildListInternal(this.root, list, false);
//        return list;
//    }

//    private LinkedList<BSTNode<T> > toLinkedListOfNodes() {
//        LinkedList<BSTNode<T>> list = new LinkedList<>();
//        buildListInternal(this.root, list, true);
//        return list;
//    }
//
//    private void buildListInternal(BSTNode<T> node, List list, boolean listOfNodes) {
//        if (node == null)
//            return;
//        buildListInternal(node.getLeft(), list, listOfNodes);
//        Object val = (listOfNodes) ? node : node.getVal();
//        list.add(val);
//        buildListInternal(node.getRight(), list, listOfNodes);
//    }


    public boolean exists(T val) {
        if (val == null)
            throw new IllegalArgumentException("Value cannot be null");
        return existsInternal(root, val);
    }

    private boolean existsInternal(BSTNode<T> node, T val) {
        if (node == null)
            return false;
        if (val.equals(node.getVal()))
            return true;
        return existsInternal(node.getLeft(), val) || existsInternal(node.getRight(), val);
    }

//    public void sort() {
//        if (size < 3)
//            return; //can't sort BST less than 3 nodes
//
//        LinkedList<BSTNode<T>> list = toLinkedListOfNodes();
//
//        //find the middle node in the list
//        int index = size / 2;
//        int mid = index;
//        BSTNode<T> newRoot = list.get(mid);
//
//        //clear any parent and child links to root
//        newRoot.clearLinks();
//
//        //assign it to root
//        this.root = newRoot;
//
//        BSTNode<T> parent;
//
//        //right side of the list from middle (root) becomes right branches off of middle
//        //set index to middle + 1
//        index = mid + 1;
//
//        //track the parent node
//        parent = newRoot;
//
//        //while index < size
//        while (index < size) {
//            //get the node at index
//            BSTNode<T> n = list.get(index);
//
//            //clear any current links
//            n.clearLinks();
//
//            //add the node as the right child of its parent
//            parent.setRight(n);
//            n.setParent(parent, false);
//
//            //increment index and set parent for next node
//            index++;
//            parent = n;
//        }
//
//        //left side of the list from middle (root) becomes left branches off of middle
//        //set index to middle - 1
//        index = mid - 1;
//
//        //track the parent node
//        parent = newRoot;
//
//        //while index is not at beginning of list
//        while (index >= 0) {
//            //get the node at index
//            BSTNode<T> n = list.get(index);
//
//            //clear any current links
//            n.clearLinks();
//
//            //add the node as the left child of its parent
//            parent.setLeft(n);
//            n.setParent(parent, true);
//
//            //decrement index and set parent for next node
//            index--;
//            parent = n;
//        }
//    }





    public void clear() {
        clearInternal(this.root);
        this.size = 0;
        this.root = null;
    }

    private void clearInternal(BSTNode<T> node) {
        if (node == null)
            return;

        node.deleteObservers();
        clearInternal(node.getLeft());
        clearInternal(node.getRight());
    }



    public T getMin() { return min; }

    public T getMax() {
        return max;
    }

    public int getSize() { return size; }

//    public int getHeight() { return getHeightInternal(root); }
//
//    private int getHeightInternal(BSTNode<T> node) {
//        if (node == null) return -1;
//
//        int l = getHeightInternal(node.getLeft());
//        int r = getHeightInternal(node.getRight());
//        int height = node.getY() + 1;
//
//        if (height > l && height > r) return height;
//        return (r > l) ? r : l;
//    }

    private boolean lt(T thisVal, T thatVal) {
        if (thisVal == null) return false;
        if (thatVal == null) return true;
        return (thisVal.compareTo(thatVal) == -1);
    }

    private boolean gt(T thisVal, T thatVal) {
        if (thisVal == null) return false;
        if (thatVal == null) return true;
        return (thisVal.compareTo(thatVal) == 1);
    }

    private boolean eq(T thisVal, T thatVal) {
        if (thisVal == null && thatVal == null) return true;
        if (thisVal == null) return false;
        if (thatVal == null) return false;
        return (thisVal.compareTo(thatVal) == 0);
    }

    @Override
    public void update(Observable o, Object arg) {
        BSTNode<T> changed = (BSTNode)o;
        BSTNode.BSTNodeChangedEvent changedEvent = (BSTNode.BSTNodeChangedEvent) arg;
        switch (changedEvent.getChangeType()) {
            case Left:
            case Right:
//                BSTNode child = (changed.getLeft() != null) ? changed.getLeft() : changed.getRight();
//                dimension(child);
                break;
            case Parent:
            case Level:
                //dimension(changed);
                break;
        }
    }

    public void reverse() {
        reverseInternal(this.root);
    }

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



//    public void sort() {
//        sortInternal(root.getLeft());
//        sortInternal(root.getRight());
//    }

//    private void sortInternal(BSTNode<T> node) {
//        if (node == null)
//            return;
//
//        sortInternal(node.getLeft());
//        sortInternal(node.getRight());
//
//        T childVal = node.getVal();
//        if (node.getParent() == null) {
//            System.out.println(node  + " does not have parent!");
//            return;
//        }
//
//        T parentVal = node.getParent().getVal();
//        if (gt(parentVal,childVal))
//            swapWithParent(node);
//    }

//    private void swapWithParent(BSTNode<T> child) {
//        /*
//                    - 5 -
//                   /     \
//                  0       9
//                 / \     / \
//               -1   4   6  10
//                   /     \
//                  1       7
//
//
//                      5
//                    /   \
//                   4     6
//                  /       \
//                 0         7
//                /           \
//               1             9
//              /               \
//            -1                 10
//
//
//
//                    0
//                   / \
//                 -1   6
//                     / \
//                    4   7
//                   / \   \
//                  3   5   9
//                         / \
//                        8   10
//                    0
//                   / \
//                 -1   6
//                     /  \
//                    3     7
//                     \     \
//                      4      9
//                       \    / \
//                        5  8  10
//                                \
//                    0            11
//                   / \
//                 -1   3
//                       \
//                        4
//                         \
//                          5
//                           \
//                            7
//                             \
//                             9
//                            /  \
//                            8  10
//
//                    0
//                   / \
//                 -1   3
//                       \
//                        4
//                         \
//                          5
//                           \
//                            7
//                             \
//                              8
//                               \
//                                9
//                                 \
//                                  10
//         */
//
//        if (child.getParent() == root)
//            return;
//                                                    //1
//        BSTNode<T> parent = child.getParent();      //4
//        BSTNode<T> top = parent.getParent();        //0
//
//        child.setParent(top);                       //    0
//                                                    //     \
//        if (gt(child.getVal(),top.getVal()))        //      1 (need to set left here)
//            top.setRight(child);                    //       \
//        else                                        //        4
//            top.setLeft(child);
//
//        parent.setParent(child);                    //
//        if (gt(parent.getVal(),child.getVal())) {
//            if (child.getRight() == null) {
//                child.setRight(parent);
//            } else {
//                BSTNode<T> insertNode = null;
//                BSTNode<T> ptr = child.getRight();
//                while (insertNode == null) {
//                    if (gt(parent.getVal(), ptr.getVal())) {
//                        if (ptr.getRight() == null)
//                            insertNode = ptr;
//                        else
//                            ptr = ptr.getRight();
//                    } else {
//                        if (ptr.getLeft() == null)
//                            insertNode = ptr;
//                        else
//                            ptr = ptr.getLeft();
//                    }
//                }
//                if (gt(parent.getVal(),insertNode.getVal()))
//                    insertNode.setRight(parent);
//                else
//                    insertNode.setLeft(parent);
//                parent.setParent(insertNode);
//            }
//        } else {
//            child.setLeft(parent);
//        }
//
//        if (parent.getLeft() == child)
//            parent.setLeft(null);
//        if (parent.getRight() == child)
//            parent.setRight(null);
//
//        sortInternal(child);
//    }


//    @Override
//    public String toString() {
//        int height = getHeight();
//        BSTNode<T>[][] matrix = new BSTNode[height][];
//
//        for (int row=0; row<height; row++) {
//            //figure out max number of nodes/columns in prev row
//            int cols = (int) Math.pow(2, row);
//            matrix[row] = new BSTNode[cols];
//        }
//
//        fillMatrix(0, matrix);
//
//        //int maxWidth = (int) Math.pow(2, height-1);
//        //int totLineLength = maxWidth;
//
//        StringBuilder sb = new StringBuilder();
//
//
//        for (int row=0; row<height; row++) {
//            BSTNode<T>[] nodes = matrix[row];
//
//            //int pos = startPos;
//            for (int col=0;col<nodes.length; col++) {
//
//                T val = (nodes[col] != null) ? nodes[col].getVal() : null;
//                String str = (val == null) ? "  " : val.toString();
//                if (str.length() == 1)
//                    str = " " + str;
//                sb.append(str);
//                sb.append("|");
//
//                /*
//
//                // min --> Minimum horizontal distance from root
//                // max --> Maximum horizontal distance from root
//                // hd  --> Horizontal distance of current node from root
//                findMinMax(tree, min, max, hd)
//                     if tree is NULL then return;
//
//                     if hd is less than min then
//                           min = hd;
//                     else if hd is greater than max then
//                          *max = hd;
//
//                     findMinMax(tree->left, min, max, hd-1);
//                     findMinMax(tree->right, min, max, hd+1);
//
//
//                printVerticalLine(tree, line_no, hd)
//                     if tree is NULL then return;
//
//                     if hd is equal to line_no, then
//                           print(tree->data);
//                     printVerticalLine(tree->left, line_no, hd-1);
//                     printVerticalLine(tree->right, line_no, hd+1);
//
//
//                totLength = 4
//
//                //row 0
//                    //nodes = 1
//                    //startPos = 2
//
//                  4
//
//                //row 1
//                    //nodes = 2
//                    //startPos = 1
//                    //addPos = 2
//                  4
//                 2  6
//
//                //row 2
//                    //nodes = 4
//                    //startPos = 0
//                    //addPos = 1
//                  4
//                 2  6
//                1 3 5 7
//
//                         100                               100
//                        /   \        Insert 40            /    \
//                      20     500    --------->          20     500
//                     /  \                              /  \
//                    10   30                           10   30
//                                                             \
//                                                              40
//                */
//            }
//
//            sb.append("\r\n");
//        }
//        return sb.toString();
//    }
//
//    private void fillMatrix(int row, BSTNode<T>[][] matrix) {
//
//        if (row == 0) {
//            //start at root
//            //start at 0,0 in matrix
//            //assign root value to 0,0
//            matrix[0][0] = root;
//            fillMatrix(1, matrix);
//            return;
//        }
//
//        //get previous rows node(s)
//        int rowPrev = row - 1;
//        BSTNode<T>[] prevNodes = matrix[rowPrev];
//
//        boolean hasChildren = false;
//
//        //iterate through each node in previous row
//        for (int col=0; col<prevNodes.length; col++) {
//
//            BSTNode<T> prevNode = prevNodes[col];
//            if (prevNode == null)
//                continue; //no parent means no children
//
//            BSTNode<T> leftChild = prevNode.getLeft();
//            BSTNode<T> rightChild = prevNode.getRight();
//            if (leftChild == null && rightChild == null)
//                continue; //also no children
//
//            //determine col for left
//            int colLeft = col * 2;
//            //determine col for right
//            int colRight = colLeft + 1;
//
//            //assign L/R
//            try {
//                matrix[row][colLeft] = leftChild;
//                matrix[row][colRight] = rightChild;
//                hasChildren = true;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        if (hasChildren)
//            fillMatrix(row+1, matrix);
//    }
}
