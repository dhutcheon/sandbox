package _testing;

import collections.lists.ILinkedList;

public class TestInjection<Integer extends Comparable<Integer>> {
    private ILinkedList<Integer> list;
    private ITestInjectionRunner preTests;
    private ITestInjectionRunner defaultTests;
    private ITestInjectionRunner postTests;

    public TestInjection() { }
    public TestInjection(ILinkedList<Integer> list) { setList(list); }

    public ILinkedList<Integer> getList() { return list; }
    public void setList(ILinkedList<Integer> list) { this.list = list; }

    public boolean hasList() { return this.list != null; }

    public ITestInjectionRunner getDefaultTests() { return defaultTests; }
    public void setDefaultTests(ITestInjectionRunner defaultTests) { this.defaultTests = defaultTests; }
    public boolean hasDefaultTests() { return this.defaultTests != null; }

    public ITestInjectionRunner getPreTests() { return preTests; }
    public void setPreTests(ITestInjectionRunner preTests) { this.preTests = preTests; }
    public boolean hasPreTests() { return this.preTests != null; }

    public ITestInjectionRunner getPostTests() { return postTests; }
    public void setPostTests(ITestInjectionRunner postTests) { this.postTests = postTests; }
    public boolean hasPostTests() { return this.postTests != null; }
}
