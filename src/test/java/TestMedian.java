import median.Median;
import org.junit.jupiter.api.Test;
import util.SortUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestMedian {


    @Test
    public void medianEven() {
        int[] arr1 = new int[]{1, 3, 5};
        int[] arr2 = new int[]{2, 4, 6};
        assertMedian(arr1, arr2, 3.5f);
    }

    @Test
    public void medianOdd() {
        int[] arr1 = new int[]{1, 3, 5};
        int[] arr2 = new int[]{2, 4, 6, 8};
        assertMedian(arr1, arr2, 4.0f);
    }

    @Test
    public void medianSameVals() {
        int[] arr1 = new int[]{1, 2, 6, 7, 10, 25};
        int[] arr2 = new int[]{1, 4, 5, 8, 9, 10, 20, 21};
        assertMedian(arr1, arr2, 7.5f);
    }

    @Test
    public void medianNegatives() {
        int[] arr1 = new int[]{-5, -1, 2, 4, 7, 10, 25, 30};
        int[] arr2 = new int[]{1, 2, 5, 8, 9, 10, 20, 21, 50};
        assertMedian(arr1, arr2, 8.0f);
    }

    @Test
    public void mergeSortOrderedArrays() {
        int[] arr1 = new int[]{-5000, -231, 25, 204, 700, 1100, 2225, 3000};
        int[] arr2 = new int[]{-50, 200, 750, 800, 900, 1200, 2100, 2100, 3000, 5000};
        int[] sorted = SortUtils.mergeSortOrderedArrays(arr1,arr2);
        print("sorted", sorted);

        TestUtils.assertSorted(sorted);
    }

    private void assertMedian(int[] arr1, int[] arr2, float assertVal) {
        print("arr1", arr1);
        print("arr2", arr2);

        Float median = Median.median(arr1, arr2);
        System.out.println("median: " + median);
        System.out.println();

        assertEquals(assertVal, median.floatValue());
    }

    private static void print(String msg, int[] arr) {
        System.out.print(msg + ": ");
        Arrays.stream(arr).forEach(i -> System.out.print(i + " "));
        System.out.println();
        System.out.println();
    }
}
