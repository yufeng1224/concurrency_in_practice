package com.yufeng.concurrency.threadcoreknowledge.deadlock;

/**
 * @description
 *      演示哲学家就餐问题导致的死锁(环路等待死锁案例)
 * @author yufeng
 * @create 2020-03-04
 */
public class DiningPhilosophers {

    public static class Philosopher implements Runnable {

        private Object leftChopstick;
        private Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("Thinking");
                    synchronized (leftChopstick) {
                        doAction("Picked up left chopstick");
                        synchronized (rightChopstick) {
                            doAction("Picked up right chopstick - eating");
                            doAction("Put down right chopstick");
                        }
                        doAction("Put down left chopstick");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + "_" + action);
            Thread.sleep((long) (Math.random() * 10));                              // 随机等待一定时间
        }
    }

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];                    // 哲学家数量
        Object[] chopsticks = new Object[philosophers.length];              // 筷子数量=哲学家数量

        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftChopstick = chopsticks[i];
            Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];

            /** 解决方案: 改变一个哲学家拿叉子的顺序(避免策略), 破坏死锁的环路等待条件 */
//            if (i == philosophers.length - 1) {
//                philosophers[i] = new Philosopher(rightChopstick, leftChopstick);     // 打破环路等待条件(避免环路的形成)
//            } else {
//                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
//            }

            philosophers[i] = new Philosopher(leftChopstick, rightChopstick);           // 该行代码会造成环路等待的死锁
            new Thread(philosophers[i], "哲学家" + (i + 1) + "号").start();
        }
    }
}
