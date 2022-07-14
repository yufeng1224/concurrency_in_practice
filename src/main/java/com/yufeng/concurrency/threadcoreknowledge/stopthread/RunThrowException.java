package com.yufeng.concurrency.threadcoreknowledge.stopthread;

/**
 * @description
 *      run 无法抛出 checked Exception, 只能用 try/catch
 * @author yufeng
 * @create 2020-02-17
 */
public class RunThrowException {

    /**
     * 普通方法可以抛出异常
     */
    public void aVoid() throws Exception {
        throw new Exception();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            /**
             * run 方法不允许抛出异常
             */
            @Override
            public void run() {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
