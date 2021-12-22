package com.yufeng.concurrency.juc.flowcontrol.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      演示使用Semaphore来控制对一个资源池的访问
 * @author yufeng
 * @create 2020-03-29
 */
public class SemaphoreDemo2 {

    private static final int MAX_AVAILABLE = 100;

    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    protected Object[] items = new Object[100];

    protected boolean[] used = new boolean[MAX_AVAILABLE];

    /**
     * 获取项目之前需要查看是否有许可证
     */
    public Object getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }


    public void putItem(Object x) {
        if (markAsUnused(x)) {
            available.release();
        }
    }


    protected synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null;
    }


    protected synchronized boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo2 pool = new SemaphoreDemo2();
        /** 获取当前资源 */
        Object currentItem = pool.getItem();
        /** 模拟当前资源使用的时间... */
        TimeUnit.SECONDS.sleep(5 );
        /** 资源使用完放回 */
        pool.putItem(currentItem);
    }
}
