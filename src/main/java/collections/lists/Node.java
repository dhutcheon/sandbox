package collections.lists;

import util.ReflectionUtils;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    private T val;

    Node(T val) { this.val = val; }


    public T getVal() { return val; }

//  public void setVal(T val) { this.val = val; }

    @Override
    public String toString() {
        if (val == null)
            return super.toString();
        return val.toString();
    }

//    @Override
//    public int compareTo(Node<T> o) {
//        if (o == null)
//            return 1;
//        T thisVal = getVal();
//        T thatVal = o.getVal();
//        return ReflectionUtils.compareTo(thisVal, thatVal);
//    }

    @Override
    public int compareTo(Node<T> o) {
        if (o == null)
            return 1;
        T thisVal = getVal();
        T thatVal = o.getVal();
        return ReflectionUtils.compareTo(thisVal, thatVal);
    }
}
