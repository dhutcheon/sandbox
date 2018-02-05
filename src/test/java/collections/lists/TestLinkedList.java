package collections.lists;

import _testing.ILinkedListTestBase;
import _testing.TestInjection;

public class TestLinkedList extends ILinkedListTestBase {
    @Override
    protected ILinkedList<Integer> initLinkedList() { return new LinkedList<>(Integer.class); }

    @Override
    protected TestInjection createInjection(String testName) { return null; }

    @Override
    protected boolean initializeListBeforeEachTest() { return true; }
}
