package com.yufeng.concurrency.juc.atomic;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description
 *      1. 演示AtomicReference的基本用法
 * @author yufeng
 * @create 2020-03-20
 */
public class AtomicReferenceDemo01 {

    private static AtomicReference<PersonalBankCard> bankCardRef = new AtomicReference<>();

    public static void main(String[] args) {
        PersonalBankCard initialBankCard = new PersonalBankCard("yufeng", new BigDecimal("1000"));
        /** 1. set(): 原子性的设置当前的值 */
        bankCardRef.set(initialBankCard);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                /** 2. get(): 原子性的读取AtomicReference中的值 */
                final PersonalBankCard personalBankCard = bankCardRef.get();

                PersonalBankCard newBankCard = new PersonalBankCard(personalBankCard.getAccountName(),
                        personalBankCard.getMoney().add(new BigDecimal("100")));

                /**
                 * 3. compareAndSet(): 比较并设置新的值
                 *    使用CAS乐观锁进行非阻塞更新
                 * */
                if (bankCardRef.compareAndSet(personalBankCard, newBankCard)) {
                    System.out.println("新的card: " + bankCardRef.get().toString());
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}


/**
 * 个人账户类
 */
class PersonalBankCard {

    private String accountName;

    private BigDecimal money;

    public PersonalBankCard(String accountName, BigDecimal money) {
        this.accountName = accountName;
        this.money = money;
    }

    public String getAccountName() {
        return accountName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "PersonalBankCard{" +
                "accountName='" + accountName + '\'' +
                ", money=" + money +
                '}';
    }
}
