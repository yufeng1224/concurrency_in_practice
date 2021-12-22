package com.yufeng.concurrency.threadcoreknowledge.singleton;

/**
 * @description
 *      单例实现方式(六)
 *          1. 懒汉式 ———— 双重检查
 *          2. 优点: 线程安全; 延迟加载; 效率较高;
 *
 *      另外不加volatile可见性也是无法保证的, 因为第二个线程也看不到 （这句话存疑? ）
 *
 * @author yufeng
 * @create 2020-03-02
 */
public class Singleton6 {

    private volatile static Singleton6 instance;

    private Singleton6() {
        /** 一系列赋值初始化操作 */
        // field1 =         // some CUP heavy logic
        // field2 =         // some value from DB
        // field3 =         // etc.
    }


    public static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    instance = new Singleton6();    // 如果不加volatile关键字, 这行新建操作会出现指令重排。
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        getInstance();
    }
}


/**
 * 为什么要用volatile关键字修饰instance?
 *    1. 因为新建对象不是原子操作
 *
 *    2. 实际上是分成了3个步骤: 1. 新建一个空的对象   2. 调用构造方法等初始化工作   3. 赋值到引用上
 *       由于指令重排序的存在, 以上顺序可能会变成 1->3->2, 也就是先将空对象赋值到引用上再进行构造方法内的初始化等工作;
 *
 *    3. 此时, 当有第二个线程执行到外部的 instance==null 该行代码时, 由于指令重排序的存在, 线程发现instance已经不为空了
 *       然后提前将该实例发布出去, 结果导致出现NPE的问题。
 *       其本质就是指令重排序, 导致构造方法内初始化工作未完成就提前暴露。
 *
 *    4. 所以需要volatile关键字禁止指令重排序, 确保初始化工作完毕!
 */



/**
 *
 * private Resource rs = null;
 *
 *
 * public Resource getExpensiveResource() {
 *     if (rs == null) {
 *         synchronized(this) {
 *             if (rs == null) {
 *                 rs = new Resource();     // 1. construct empty resource  2. assign to rs  3. call constructor (指令重排)
 *                                             提前暴露对象, 是重排序导致的。
 *             }
 *         }
 *     }
 * }
 *
 */

