package com.yufeng.concurrency.threadcoreknowledge.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      转账时遇到死锁的案例演示: 动态的锁顺序死锁
 * @author yufeng
 * @create 2020-03-04
 */
public class DeadLock02 implements Runnable {

    int flag = 1;
    static Object lock = new Object();

    static Account a = new Account(500);
    static Account b = new Account(500);

    @Override
    public void run() {
        if (flag == 1) {
            transferMoneyFail(a, b, 200);
        }

        if (flag == 0) {
            transferMoneyFail(b, a, 200);
        }
    }

    /**
     * 失败的转账方法
     */
    public static void transferMoneyFail(Account from, Account to, int amount) {

        synchronized (from) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (to) {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足,转账失败。");
                    return;
                }

                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账:" + amount + "元");
            }
        }
    }

    /**
     * 通过hash值来决定获取锁的顺序、冲突时需要"加时赛"
     */
    public static void transferMoney(Account from, Account to, int amount) {

        class Helper {
            public void transfer() {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足,转账失败。");
                    return;
                }

                from.balance -= amount;
                to.balance = to.balance  + amount;
                System.out.println("成功转账:" + amount + "元");
            }
        }

        int fromHash = System.identityHashCode(from);       // 获取hash值
        int toHash = System.identityHashCode(to);           // 获取hash值

        /** 始终从小到大获取锁 */
        if (fromHash < toHash) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {                     // 这样不会发生顺序相反
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        } else {                                            // hash值一样， 冲突需要做额外处理(使用数据库主键更方便)
            synchronized (lock) {
                synchronized (to) {
                    synchronized (from) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }

    static class Account {
        int balance;

        public Account (int balance) {
            this.balance = balance;
        }
    }
}
