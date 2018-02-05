package util;

import org.junit.jupiter.api.Test;
import util.ReflectionUtils;

import static org.junit.jupiter.api.Assertions.*;

public class TestReflectionUtils {
    private static final double TEST_VAL = 1000d;



    @Test
    public void compareToEQ()  {
        ReflectionUtils.compareTo("test","test");
    }

    @Test
    public void compareToLT()  {
        assertEquals(1, ReflectionUtils.compareTo(0d,-1d));
    }

    @Test
    public void compareToGT()  {
        assertEquals(-1, ReflectionUtils.compareTo(0l,1l));
    }

    @Test
    public void eq()  {
        assertTrue(ReflectionUtils.eq(500,500));
    }

    @Test
    public void lt()  {
        assertTrue(ReflectionUtils.lt(500,600));
    }

    @Test
    public void gt()  { assertTrue(ReflectionUtils.gt(500,400)); }

    @Test
    public void gtNulls()  {
        assertTrue(ReflectionUtils.gt(500,null));
        assertFalse(ReflectionUtils.gt(null,null));
        assertFalse(ReflectionUtils.gt(null,500));
    }

    @Test
    public void ltNulls()  {
        assertFalse(ReflectionUtils.lt(500,null));
        assertFalse(ReflectionUtils.lt(null,null));
        assertTrue(ReflectionUtils.lt(null,500));
    }

    @Test
    public void eqNulls()  {
        assertFalse(ReflectionUtils.eq(500,null));
        assertTrue(ReflectionUtils.eq(null,null));
        assertFalse(ReflectionUtils.eq(null,500));
    }
}
