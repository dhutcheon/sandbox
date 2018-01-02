import collections.lists.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestNode {
    private static final String TEST_STRING = "testing....1....2....3!";

    private Node<String> node;
    @BeforeEach
    public void beforeEach() {
        node = createNode(TEST_STRING);
    }


    @Test
    public void getVal() {
        assertEquals(TEST_STRING, node.getVal());
    }

    @Test
    public void compareToEQ()  {
        Node eq = createNode(TEST_STRING);
        assertEquals(0, node.compareTo(eq));
    }

    @Test
    public void compareToLT()  {
        Node<Integer> n = createNode(0);
        Node<Integer> lt = createNode(-1);
        assertEquals(1, n.compareTo(lt));
    }

    @Test
    public void compareToGT()  {
        Node<Integer> n = createNode(0);
        Node<Integer> gt = createNode(1);
        assertEquals(-1, n.compareTo(gt));
    }

    @Test
    public void compareNull()  {
        assertEquals(1, node.compareTo(null));
    }

    @Test
    public void nodeToString()  {
        assertEquals(TEST_STRING, node.toString());
    }

    private <T extends Comparable> Node<T> createNode(T val) {
        try {
            Class<?> cls = Node.class;
            @SuppressWarnings("unchecked")
            Class<? extends Node<T>> nodeCls = (Class<? extends Node<T>>) cls;
            Constructor<? extends Node<T>> ctor = nodeCls.getDeclaredConstructor(Comparable.class);
            ctor.setAccessible(true);
            Node<T> node = ctor.newInstance(val);
            return node;
        } catch (Exception ex) {
            fail(ex.getMessage(), ex);
            throw new IllegalStateException("Could not create node");
        }
    }
}
