package _testing;

import collections.lists.ILinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ILinkedListTestBase {

    private ILinkedList<Integer> list;

    protected abstract ILinkedList<Integer> initLinkedList();
    protected abstract TestInjection createInjection(String testName);
    protected abstract boolean initializeListBeforeEachTest();


    @BeforeEach
    public void beforeEach() {
        if (!initializeListBeforeEachTest())
            return;

        buildDefaultList();
    }

    protected ILinkedList<Integer> getLinkedList(String testName) {
        buildDefaultList();
        return list;
    }

    private void buildDefaultList() {
        list = initLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    private void executeTests(String testName, ITestInjectionRunner defaultTests) {
        TestInjection testInjection = createInjection(testName);
        if (testInjection == null) {
            if (!initializeListBeforeEachTest())
                list = getLinkedList(testName);
            testInjection = new TestInjection(list);
            testInjection.setDefaultTests(defaultTests);
        } else {
            if (testInjection.hasList())
                list = testInjection.getList();
            else if (!initializeListBeforeEachTest())
                list = getLinkedList(testName);
            if (!testInjection.hasDefaultTests())
                testInjection.setDefaultTests(defaultTests);
        }

        executeRunner(testInjection.getPreTests(), testName, list);
        executeRunner(testInjection.getDefaultTests(), testName, list);
        executeRunner(testInjection.getPostTests(), testName, list);

    }
    private void executeRunner(ITestInjectionRunner runner, String testName, ILinkedList<Integer> list) {
        if (runner == null)
            return;
        runner.runTests(testName, list);
    }

    @Test
    public void add() {
        executeTests("add", (testName, list) -> {
            list.add(4);
            assertEquals(4, (int)list.get(list.size() - 1));
        });
    }



    @Test
    public void get() {
        executeTests("get", (testName, list) -> {
            String listStr = list.toString();
            System.out.println(listStr);
            System.out.println();

            assertTrue(list.get(0) == 1);
            assertTrue(list.get(1) == 2);
            assertTrue(list.get(2) == 3);
        });

    }

    @Test
    public void size() {
        executeTests("size", (testName, list) -> {
            assertEquals(3, list.size());

            list.add(8);
            assertEquals(4, list.size());

            list.insert(10, 3);
            assertEquals(5, list.size());

            list.remove();
            assertEquals(4, list.size());
        });
    }

    @Test
    public void iterator() {
        executeTests("iterator", (testName, list) -> {
            Iterator<Integer> iterator = list.iterator();
            assertTrue(iterator.hasNext());

            int i = 1;
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                assertEquals(i, next.intValue());
                i++;
            }

            assertEquals((i - 1), list.size());
            assertFalse(iterator.hasNext());
        });
    }

    @Test
    public void toArray() {
        executeTests("iterator", (testName, list) -> {
            Integer[] arr = list.toArray();
            assertNotNull(arr);
            assertEquals(list.size(), arr.length);
            for (int i = 0; i < list.size(); i++) {
                int expected = list.get(i);
                int actual = arr[i];
                assertEquals(expected, actual);
            }
        });
    }

    @Test
    public void sort() {
        executeTests("iterator", (testName, list) -> {
            list.insert(0, 3);
            list.insert(3, 2);
            list.insert(-1, 3);

            System.out.println("Before Sort: " + list);
            list.sort();
            String sortedList = list.toString();
            System.out.println("Sorted:      " + sortedList);
            System.out.println();

            assertEquals("[-1, 0, 1, 2, 3, 3]", sortedList);
        });
    }

    @Test
    public void sortFromReverse() {
        executeTests("insert", (testName, list) -> {
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

            TestUtils.assertSorted(list);

            System.out.println("Sorted List: " + list);
        });
    }

    @Test
    public void insert() {
        executeTests("insert", (testName, list) -> {
            assertInsert(0,0);
            assertInsert(1,1);
            assertInsert(6, list.size());
            assertInsert(5,(list.size()-1));
        });
    }

    private void assertInsert(int val, int insertIndex) {
        System.out.println("Insert " + val + " at index " + insertIndex);
        list.insert(val, insertIndex);

        System.out.println("List: " + list);
        System.out.println("Size: " + list.size());
        assertEquals(val, (int) list.get(insertIndex));
        System.out.println();
    }

    @Test
    public void reverse() {
        executeTests("reverse", (testName, list) -> {
            System.out.println("Before Reverse: " + list);
            list.reverse();
            System.out.println("Reversed:       " + list);
            System.out.println();

            assertTrue(list.get(0) == 3);
            assertTrue(list.get(1) == 2);
            assertTrue(list.get(2) == 1);
        });
    }

    @Test
    public void remove() {
        executeTests("remove", (testName, list) -> {
            System.out.println(list);
            System.out.println();

            TestUtils.assertRemove(list, 3);
            TestUtils.assertRemove(list, 2);
            TestUtils.assertRemove(list, 1);
        });
    }

    @Test
    public void removeAt() {
        executeTests("removeAt", (testName, list) -> {
            list.add(4);

            System.out.println(list);
            System.out.println();

            TestUtils.assertRemoveAt(list, 1, 2);
            TestUtils.assertRemoveAt(list, 2, 4);
            TestUtils.assertRemoveAt(list, 0, 1);
        });
    }

    @Test
    public void isEmpty() {
        executeTests("isEmpty", (testName, list) -> {
            System.out.println(list.isEmpty());
            System.out.println();

            assertFalse(list.isEmpty());
            list.remove();
            list.remove();
            list.remove();

            System.out.println(list.isEmpty());
            System.out.println();

            assertTrue(list.isEmpty());
        });
    }




}
