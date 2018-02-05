import bst.BinarySearchTree;
import collections.lists.LinkedList;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(Integer.class, 0);

        /*
                    0
                   / \
                 -1   6
                     / \
                    4   7
                   / \   \
                  3   5   9
                         / \
                        8   10

         */

        bst.insert(6);
        bst.insert(4);
        bst.insert(3);
        bst.insert(5);
        bst.insert(7);
        bst.insert(9);
        bst.insert(10);
        bst.insert(8);
        bst.insert(-1);

        System.out.println(bst);

        System.out.println("4 exists: " + bst.exists(4));
        System.out.println("-8 exists: " + bst.exists(-8));
        System.out.println();

        System.out.println("min:    " + bst.getMin());
        System.out.println("max:    " + bst.getMax());
        System.out.println("size:   " + bst.getSize());
        System.out.println("height: " + bst.getHeight());
        System.out.println();

        System.out.println("Reversing.....");
        bst.reverse();
        System.out.println(bst);
        System.out.println();

        LinkedList<Integer> values = bst.toLinkedList();
        StringBuilder sb = new StringBuilder("List of values: ");
        values.spliterator().forEachRemaining(v -> sb.append(v + " "));
        System.out.println(sb);
        System.out.println();

        System.out.println("Balancing.....");
        bst.balance();
        System.out.println(bst);
        System.out.println();

        System.out.println("min:    " + bst.getMin());
        System.out.println("max:    " + bst.getMax());
        System.out.println("size:   " + bst.getSize());
        System.out.println("height: " + bst.getHeight());
        System.out.println();

        System.out.println("Sorting.....");
        bst.sort();
        System.out.println(bst);
        System.out.println();

        System.out.println("min:    " + bst.getMin());
        System.out.println("max:    " + bst.getMax());
        System.out.println("size:   " + bst.getSize());
        System.out.println("height: " + bst.getHeight());
        System.out.println();
    }

    /*
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(Integer.class, 0);
//        bst.insert(-1);
//        bst.insert(1);
//        bst.insert(2);
//        bst.insert(1);
//        bst.insert(4);
//        bst.insert(3);
//        bst.insert(-3);
//        bst.insert(-2);


                    0
                   / \
                 -1   6
                     / \
                    4   7c                                                   asa
                   / \   \
                  3   5   9
                         / \
                        8   10



        bst.insert(6);
        bst.insert(4);
        bst.insert(3);
        bst.insert(5);
        bst.insert(7);
        bst.insert(9);
        bst.insert(10);
        bst.insert(8);
        bst.insert(-1);

        System.out.println(bst);

        System.out.println("4 exists: " + bst.exists(4));
        System.out.println("-8 exists: " + bst.exists(-8));
        System.out.println();

        System.out.println("min:    " + bst.getMin());
        System.out.println("max:    " + bst.getMax());
        System.out.println("size:   " + bst.getSize());
        System.out.println("height: " + bst.getHeight());
        System.out.println();

        System.out.println("Reversing.....");
        bst.reverse();
        System.out.println(bst);
        System.out.println();

        System.out.println("Balancing.....");
        bst.balance();
        System.out.println(bst);
        System.out.println();

        System.out.println("size:   " + bst.getSize());
        System.out.println("height: " + bst.getHeight());

        System.out.println("Removing 8.....");
        bst.remove(8);
        System.out.println(bst);
        System.out.println();

        System.out.println("8 exists: " + bst.exists(8));
        System.out.println("6 exists: " + bst.exists(6));
        System.out.println();

        System.out.println("min:    " + bst.getMin());
        System.out.println("max:    " + bst.getMax());
        System.out.println("size:   " + bst.getSize());
        System.out.println("height: " + bst.getHeight());
        System.out.println();

        System.out.println("Adding 1.....");
        bst.insert(1);
        System.out.println(bst);
        System.out.println();

        System.out.println("Removing 3.....");
        bst.remove(3);
        System.out.println(bst);
        System.out.println();

        System.out.println("3 exists: " + bst.exists(3));
        System.out.println("1 exists: " + bst.exists(1));
        System.out.println("-1 exists: " + bst.exists(-1));
        System.out.println();

        System.out.println("min:    " + bst.getMin());
        System.out.println("max:    " + bst.getMax());
        System.out.println("size:   " + bst.getSize());
        System.out.println("height: " + bst.getHeight());

        bst.sort();
        System.out.println(bst);
        System.out.println();
        System.out.println("min:    " + bst.getMin());
        System.out.println("max:    " + bst.getMax());
        System.out.println("size:   " + bst.getSize());
        System.out.println("height: " + bst.getHeight());
        System.out.println();

        List values = bst.toLinkedList();
        StringBuilder sb = new StringBuilder("List of values: ");
        values.stream().forEach(v -> sb.append(v + " "));
        System.out.println(sb);
    }
    */

    /*
    public static void main(String[] args) {
        //Median.run();

        boolean match = PatternMatch.matchFound("abce", "ab");
        System.out.println("Found pattern ab in abce: " + match);
        System.out.println();

        LinkedList<Integer> list = new LinkedList<>(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        System.out.println();

        int val = list.remove();
        System.out.println("Removed: " + val);
        System.out.println(list);
        System.out.println();

        val = list.remove();
        System.out.println("Removed: " + val);
        System.out.println(list);
        System.out.println();

        val = list.remove();
        System.out.println("Removed: " + val);
        System.out.println(list);
        System.out.println();

        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        System.out.println("Before Reverse: " + list);
        list.reverse();
        System.out.println("Reversed:       " + list);
        System.out.println();

        list.insert(0, 0);
        System.out.println("Insertion 0: " + list);
        list.insert(5, list.size());
        System.out.println("Insertion " + (list.size()-1) + ": " + list);
        list.insert(6, 4);
        System.out.println("Insertion 4: " + list);
        System.out.println();

        System.out.println("Before Sort: " + list);
        list.sort();
        System.out.println("Sorted:      " + list);
        System.out.println();
    }
    */
}
