package com.yufeng.concurrency.threadcoreknowledge.stopthread;

/**
 * @description
 *      生产最佳实践(传递中断方式)
 *         1. 底层方法传递中断, 不对中断做处理。 对外层层抛出, 直到顶层方法run()中做处理;
 *         2. run()中会进行try/catch;
 * @author yufeng
 * @create 2021-02-17
 */
public class RightWayStopThreadInProd implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("go");

            try {
                throwInMethod();
                //throwInMethodWrongWay();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();         // 捕捉子方法异常, 并恢复中断, 用于while循环校验并跳出当前循环体
                System.out.println("保存日志...");            // 保存日志等操作
                e.printStackTrace();
            }
        }
    }

    /**
     * 正确方式: 底层方法传递中断, 不对中断进行处理。 抛出到顶层方法run中做处理
     */
    private void throwInMethod() throws InterruptedException{
        Thread.sleep(2000);
    }


    /**
     * 错误方式: 吞掉中断，那么顶层run方法中将无法感知到
     */
    private void throwInMethodWrongWay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
