package collections.lists;

import _testing.ILinkedListTestBase;
import _testing.TestInjection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDoubleLinkedList extends ILinkedListTestBase {
    @Override
    protected ILinkedList<Integer> initLinkedList() { return new DoubleLinkedList<>(Integer.class); }

    @Override
    protected TestInjection createInjection(String testName) {
        TestInjection testInjection = new TestInjection();
        switch (testName) {
            case "add":
                testInjection.setPreTests((testName1, list) -> {
                    DoubleLinkedList<Integer> doubleLinkedList = (DoubleLinkedList<Integer>)list;
                    int head = doubleLinkedList.getHead();
                    int tail = doubleLinkedList.getTail();

                    assertEquals(1, head);
                    assertEquals(3, tail);
                });
                break;

            default:
                break;
        }

        return testInjection;
    }

    @Override
    protected boolean initializeListBeforeEachTest() { return false; }
}
