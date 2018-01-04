import collections.lists.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLinkedList {

    private LinkedList<Integer> list = new LinkedList<>();

    @BeforeEach
    public void beforeEach() {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void get() {
        String listStr = list.toString();
        System.out.println(listStr);
        System.out.println();

        assertTrue(list.get(0) == 1);
        assertTrue(list.get(1) == 2);
        assertTrue(list.get(2) == 3);
    }

    @Test
    public void size() {
        assertEquals(3,list.size());

        list.add(8);
        assertEquals(4,list.size());

        list.insert(10, 3);
        assertEquals(5,list.size());

        list.remove();
        assertEquals(4,list.size());
    }

    @Test
    public void sort() {
        list.insert(0, 3);
        list.insert(3, 2);
        list.insert(-1, 3);

        System.out.println("Before Sort: " + list);
        list.sort();
        String sortedList = list.toString();
        System.out.println("Sorted:      " + sortedList);
        System.out.println();

        assertEquals("[-1, 0, 1, 2, 3, 3]",sortedList);
    }

    @Test
    public void sortFromReverse() {
        list.clear();
        list.add(10);
        list.add(9);
        list.add(8);
        list.add(7);
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(0);
        list.add(-1);

        System.out.println(list);
        System.out.println();

        list.sort();

        System.out.println("Sorted List: " + list);
    }

    @Test
    public void insert() {
        list.insert(0, 0);
        System.out.println("Insertion 0: " + list);
        assertEquals(0, (int)list.get(0));

        list.insert(5, list.size());
        System.out.println("Insertion 5: " + list);
        assertEquals(5,(int)list.get(list.size()-1));

        list.insert(6, 4);
        System.out.println("Insertion 4: " + list);
        assertEquals(6,(int)list.get(4));
        System.out.println();
    }

    @Test
    public void reverse() {
        System.out.println("Before Reverse: " + list);
        list.reverse();
        System.out.println("Reversed:       " + list);
        System.out.println();

        assertTrue(list.get(0) == 3);
        assertTrue(list.get(1) == 2);
        assertTrue(list.get(2) == 1);
    }

    @Test
    public void remove() {
        System.out.println(list);
        System.out.println();

        TestUtils.assertRemove(list, 3);
        TestUtils.assertRemove(list, 2);
        TestUtils.assertRemove(list, 1);
    }

    @Test
    public void removeAt() {
        list.add(4);

        System.out.println(list);
        System.out.println();

        TestUtils.assertRemoveAt(list,1,2);
        TestUtils.assertRemoveAt(list,2,4);
        TestUtils.assertRemoveAt(list,0,1);
    }

    @Test
    public void isEmpty() {
        System.out.println(list.isEmpty());
        System.out.println();

        assertFalse(list.isEmpty());
        list.remove();
        list.remove();
        list.remove();

        System.out.println(list.isEmpty());
        System.out.println();

        assertTrue(list.isEmpty());
    }
}
