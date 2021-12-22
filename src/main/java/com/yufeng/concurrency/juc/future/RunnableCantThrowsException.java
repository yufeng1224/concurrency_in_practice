package com.yufeng.concurrency.juc.future;

import java.io.File;

/**
 * @description
 *      在run方法中无法抛出checked Exception
 * @author yufeng
 * @create 2020-03-13
 */
public class RunnableCantThrowsException {

    /**
     * 普通的方法可以抛出异常
     */
    public static void normal() throws Exception {
        File f = new File("aa.txt");
        f.createNewFile();
    }


    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();

        try {
            normal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
