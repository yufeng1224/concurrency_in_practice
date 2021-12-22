package com.yufeng.concurrency.threadcoreknowledge.jmm;


import java.util.concurrent.CountDownLatch;

/**
 * @description
 *      演示重排序的现象, "直到达到某个条件才停止, 测试小概率事件"
 *      重排序分析: 这4行代码的执行顺序决定了最终x和y的结果，一共有3种情况:
 *         1. a = 1, x = 0, b = 1, y = 1, 最终结果是x = 0, y = 1  (先执行线程a,再执行线程b)
 *         2. b = 1, y = 0, a = 1, x = 1, 最终结果是x = 1, y = 0  (先执行线程b,再执行线程a)
 *         3. b = 1, a = 1, x = 1, y = 1, 最终结果是x = 1, y = 1  (先执行线程1的 a=1 操作, 再执行线程2, 最后执行线程1的 x=b 操作)
 *      以上的结果是针对a = 1会在x = b之前执行, 以及b = 1会在y = a前执行
 *
 *      会出现x = 0, y = 0, 那是因为重排序发生了, 4行代码的执行顺序变成了
 *         4. y = a, a = 1, x = b, b = 1;
 *
 * @author yufeng
 * @create 2020-02-27
 */
public class OutOfOrderExecution {

    private static  int x = 0, y = 0;
    private static  int a = 0, b = 0;

    public static void main(String[] args) throws Exception {

        int count = 0;
        for (;;) {
            count ++;

            /** 清零工作 */
            x = 0; y = 0; a = 0; b = 0;

            CountDownLatch latch = new CountDownLatch(1);
            Thread one = new Thread(() -> {
                try {
                    //System.out.println("Thread one await");
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                a = 1;
                x = b;
            });


            Thread two = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /** 潜在的指令重排, y=a 指令先执行, 再执行 b=1， 因为在同一个线程中，会存在指令优化 */
                b = 1;
                y = a;
            });

            one.start();
            two.start();

            latch.countDown();
            one.join();
            two.join();

            if (x == 0 && y == 0) {
                System.out.println("执行了" + count + "次");
                System.out.println("x = " + x + ", y = " + y);
                break;
            }
        }
    }
}


/**
 * 重排序的好处: 提高处理速度。 举例: 对比重排序前后的指令优化
 *
 * 代码:                          Instructions
 *     a = 3;                    -> Load a
 *     b = 2;                    -> Set to 3
 *     a = a + 1;                -> Store a
 *                               -> Load b
 *                               -> Set to 2
 *                               -> Store b
 *                               -> Load a
 *                               -> Set to 4
 *                               -> Store 4
 *
 * 指令重排
 *     a = 3;                    -> Load a
 *     a = a + 1;                -> Set to 3
 *     b = 2;                    -> Set to 4
 *                               -> Store a
 *                               -> Load b
 *                               -> Set to 2
 *                               -> Store b
 *
 * 优化过后, 指令变少了。由此可见, 重排序能明显地提高处理速度!
 */

