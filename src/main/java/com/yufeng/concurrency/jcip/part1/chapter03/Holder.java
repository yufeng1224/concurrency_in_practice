package com.yufeng.concurrency.jcip.part1.chapter03;

/**
 * @description
 *    由于未被正确发布, 因此这个类可能出现故障
 * @author yufeng
 * @create 2020-04-25
 */
public class Holder {

    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n) {
            throw new AssertionError("This statement is false.");
        }
    }

}
