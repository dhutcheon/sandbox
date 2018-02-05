package collections.lists;

import collections.lists.LinkedListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestNode {
    private static final String TEST_STRING = "testing....1....2....3!";

    private LinkedListNode<String> linkedListNode;
    @BeforeEach
    public void beforeEach() {
        linkedListNode = createNode(TEST_STRING);
    }


    @Test
    public void getVal() {
        assertEquals(TEST_STRING, linkedListNode.getVal());
    }

    @Test
    public void compareToEQ()  {
        LinkedListNode eq = createNode(TEST_STRING);
        assertEquals(0, linkedListNode.compareTo(eq));
    }

    @Test
    public void compareToLT()  {
        LinkedListNode<Integer> n = createNode(0);
        LinkedListNode<Integer> lt = createNode(-1);
        assertEquals(1, n.compareTo(lt));
    }

    @Test
    public void compareToGT()  {
        LinkedListNode<Integer> n = createNode(0);
        LinkedListNode<Integer> gt = createNode(1);
        assertEquals(-1, n.compareTo(gt));
    }

    @Test
    public void compareNull()  {
        assertEquals(1, linkedListNode.compareTo(null));
    }

    @Test
    public void nodeToString()  {
        assertEquals(TEST_STRING, linkedListNode.toString());
    }

    private <T extends Comparable<T>> LinkedListNode<T> createNode(T val) {
        try {
            Class<?> cls = LinkedListNode.class;
            @SuppressWarnings("unchecked")
            Class<? extends LinkedListNode<T>> nodeCls = (Class<? extends LinkedListNode<T>>) cls;
            Constructor<? extends LinkedListNode<T>> ctor = nodeCls.getDeclaredConstructor(Comparable.class);
            ctor.setAccessible(true);
            LinkedListNode<T> linkedListNode = ctor.newInstance(val);
            return linkedListNode;
        } catch (Exception ex) {
            fail(ex.getMessage(), ex);
            throw new IllegalStateException("Could not create linkedListNode");
        }
    }
}
