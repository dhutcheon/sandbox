package _testing;

import collections.lists.ILinkedList;
import util.ReflectionUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtils {

    public static void assertSorted(ILinkedList<Integer> list) {
        Integer[] array = list.toArray();
        final Comparable<Integer>[] prev = new Comparable[]{array[0]};
        Arrays.stream(array).forEach(i -> {
            //assertTrue(prev[0] <= i);
            assertTrue(ReflectionUtils.gt(i, prev[0]) || ReflectionUtils.eq(i, prev[0]));
            prev[0] = i;
        });
    }

    public static void assertSorted(int[] array) {
        final int[] prev = new int[]{array[0]};
        Arrays.stream(array).forEach(i -> {
            //assertTrue(prev[0] <= i);
            assertTrue(ReflectionUtils.gt(i, prev[0]) || ReflectionUtils.eq(i, prev[0]));
            prev[0] = i;
        });
    }




    public static <T extends Comparable<T>> void assertRemove(ILinkedList<T> list, T removedVal) {
        T val = list.remove();

        System.out.println("Removed: " + val);
        System.out.println(list);
        System.out.println();

        assertTrue(val == removedVal);
    }

    public static <T extends Comparable<T>> void assertRemoveAt(ILinkedList<T> list, int index, T removedVal) {
        T val = list.removeAt(index);

        System.out.println("Removed: " + val + " at index " + index);
        System.out.println(list);
        System.out.println();

        assertTrue(val == removedVal);
    }
}
