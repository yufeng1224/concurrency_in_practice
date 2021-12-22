package com.yufeng.concurrency.juc.collections.predecessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 *      1. 演示Collections.synchronizedList(new ArrayList<E>())
 *      2. 能够保证线程安全, 底层源码使用到了synchronized同步代码块
 * @author yufeng
 * @create 2020-03-26
 */
public class SynList {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        list.add(5);
        System.out.println(list.get(0));

        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());
        System.out.println(objectObjectMap);
    }
}
