package com.yufeng.concurrency.juc.threadlocal;

/**
 * @description
 *      演示ThreadLocal用法2: 避免传递参数的麻烦
 * @author yufeng
 * @create 2020-03-15
 */
public class ThreadLocalNormalUsage07 {

    public static void main(String[] args) {
        new Service1().process();
    }

}

class Service1 {
    public void process() {
        User user = new User("超哥");
        UserContextHolder.holder.set(user);         // holder存放用户对象
        new Service2().process();
    }
}

class Service2 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service2拿到用户名：" + user.name);

        ThreadSafeFormatter.dateFormatThreadLocal.get();
        new Service3().process();
    }
}

class Service3 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到用户名：" + user.name);

        // 使用完成之后需要删除!
        UserContextHolder.holder.remove();
    }
}

/**
 * holder: 在编程中通常代表持有某些对象, 其他类可以从此处获取所需
 */
class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}



