package com.yufeng.concurrency.threadcoreknowledge.deadlock;

import java.util.Random;
import com.yufeng.concurrency.threadcoreknowledge.deadlock.DeadLock02.Account;

/**
 * @description
 *      模拟多人同时转账，依然很危险(发生死锁几率不高，但是危害很大)
 * @author yufeng
 * @create 2020-03-04
 */
public class DeadLock03 {

    private static final int NUM_ACCOUNTS = 500;            // 账户数量
    private static final int NUM_MONEY = 1000;              // 账户金额
    private static final int NUM_ITERATIONS = 1000000;      // 转账次数
    private static final int NUM_THREADS = 20;              // 线程数

    public static void main(String[] args) {
        Random random = new Random();
        Account[] accounts = new Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length; i ++) {
            accounts[i] = new Account(NUM_MONEY);
        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i ++) {
                    int fromAcct = random.nextInt(NUM_ACCOUNTS);
                    int toAcct = random.nextInt(NUM_ACCOUNTS);
                    int amount = random.nextInt(NUM_MONEY);

                    //TransferMoney.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                     DeadLock02.transferMoneyFail(accounts[fromAcct], accounts[toAcct], amount);
                }

                System.out.println("运行结束");
            }
        }

        for (int i = 0; i < NUM_THREADS; i ++) {
            new TransferThread().start();
        }
    }
}
