package com.yufeng.concurrency.threadcoreknowledge.startthread;


/**
 * @description
 *     演示不能两次调用start方法，否则会抛出异常
 *     java.lang.IllegalThreadStateException
 * @author yufeng
 * @create 2020-02-14
 *
 */
public class CantStartTwice {

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();

        /** IllegalThreadStateException */
        thread.start();
    }
}
