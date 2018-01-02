import collections.Tuple;
import collections.lists.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

public class TestTuple {
    private static final String TEST_STRING = "testing....1....2....3!";

    private Tuple<Integer,Integer> tuple;
    @BeforeEach
    public void beforeEach() {
        tuple = new Tuple<>(1,2);
        //System.out.println("hash code: " + tuple.hashCode());
    }


    @Test
    public void x() {
        assertEquals(1, (int)tuple.getX());
    }

    @Test
    public void y() {
        assertEquals(2, (int)tuple.getY());
    }

    @Test
    public void equals()  {
        assertFalse(tuple.equals(null));
        assertTrue(tuple.equals(tuple));

        assertFalse(tuple.equals("some random value"));

        Tuple<Double,Double> dbl = new Tuple<>(1d, 2d);
        assertFalse(tuple.equals(dbl));

        Tuple<Integer,Integer> eq = new Tuple<>(1,2);
        assertTrue(tuple.equals(eq));
    }

    @Test
    public void tupleToString() {
        assertEquals("1, 2", tuple.toString());
    }

    @Test
    public void tupleHashCode() {
        assertEquals(994,tuple.hashCode());
    }


}
