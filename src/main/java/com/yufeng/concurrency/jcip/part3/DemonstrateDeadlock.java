package com.yufeng.concurrency.jcip.part3;

import java.util.Random;
import com.yufeng.concurrency.jcip.part3.DynamicOrderDeadlock.*;

/**
 * @description
 *      在典型条件下会发生死锁的循环
 * @author yufeng
 * @create 2020-03-06
 */
public class DemonstrateDeadlock {

    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) {
        final Random rnd = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        class TransferThread extends Thread {

            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i ++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);

                    DollarAmount amount = new DollarAmount(rnd.nextInt(1000));

                    try {
                        DynamicOrderDeadlock.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                    } catch (InsufficientFundsException e) {

                    }
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i ++) {
            new TransferThread().start();
        }
    }
}
