package com.yufeng.concurrency.threadcoreknowledge.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @description
 *      定义自己的MyUncaughtExceptionHandler
 * @author yufeng
 * @create 2020-02-23
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private String name;


    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, "线程异常，终止啦" + t.getName());
        System.out.println(name + "捕获了异常" + t.getName() + "异常");
    }
}
