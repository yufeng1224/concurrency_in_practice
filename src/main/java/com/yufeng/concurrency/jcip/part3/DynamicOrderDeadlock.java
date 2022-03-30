package com.yufeng.concurrency.jcip.part3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description
 *      动态的锁顺序死锁
 * @author yufeng
 * @create 2020-03-06
 */
public class DynamicOrderDeadlock {

    /**
     * 容易发生死锁
     */
    public static void transferMoney(Account fromAccount, Account toAccount,
                                     DollarAmount amount) throws InsufficientFundsException {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                } else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }

    static class DollarAmount implements Comparable<DollarAmount> {
        // Needs implementation

        public DollarAmount(int amount) {
        }

        public DollarAmount add(DollarAmount d) {
            return null;
        }

        public DollarAmount subtract(DollarAmount d) {
            return null;
        }

        @Override
        public int compareTo(DollarAmount o) {
            return 0;
        }
    }

    static class Account {

        private DollarAmount balance;

        private final int acctNo;

        private static final AtomicInteger sequence = new AtomicInteger();

        public Account() {
            acctNo = sequence.incrementAndGet();
        }

        void debit(DollarAmount d) {
            balance = balance.subtract(d);
        }

        void credit(DollarAmount d) {
            balance = balance.add(d);
        }

        DollarAmount getBalance() {
            return balance;
        }

        int getAcctNo() {
            return acctNo;
        }
    }

    static class InsufficientFundsException extends Exception {
    }

}
