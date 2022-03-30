package com.yufeng.concurrency.threadcoreknowledge.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *      演示活锁问题
 * @author yufeng
 * @create 2020-03-04
 */
public class LiveLock {

    static class Spoon {

        private Diner owner;

        public Spoon(Diner owner) {
            this.owner = owner;
        }

        public void setOwner(Diner owner) {
            this.owner = owner;
        }

        public synchronized void use() {
            System.out.printf("%s吃完了!", owner.name);
        }
    }

    static class Diner {
        private String name;
        private boolean isHungry;

        public Diner(String name) {
            this.name = name;
            isHungry = true;
        }

        public void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                if (spoon.owner != this) {
                    try {
                        System.out.println("由于没有拿到汤勺, 配偶线程在反复执行此块代码!");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                Random random = new Random();
                // 不加 random.nextInt(10) < 9 , 会产生活锁
                if (spouse.isHungry && random.nextInt(10) < 9) {
                    System.out.println(name + ": 亲爱的" + spouse.name + "你先吃吧");
                    spoon.setOwner(spouse);
                    continue;
                }

                spoon.use();
                isHungry = false;
                System.out.println(name + ": 我吃完了");
                spoon.setOwner(spouse);
            }
        }
    }

    public static void main(String[] args) {
        Diner husband = new Diner("牛郎");
        Diner wife = new Diner("织女");

        Spoon spoon = new Spoon(husband);
        new Thread(() -> husband.eatWith(spoon, wife)).start();
        new Thread(() -> wife.eatWith(spoon, husband)).start();
    }

}
