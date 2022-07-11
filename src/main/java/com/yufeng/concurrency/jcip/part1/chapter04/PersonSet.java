package com.yufeng.concurrency.jcip.part1.chapter04;

import com.yufeng.concurrency.jcip.annotations.GuardedBy;

import java.util.HashSet;
import java.util.Set;

/**
 * @description
 *     通过封闭机制来确保线程安全
 * @author yufeng
 * @create 2020-04-27
 */
public class PersonSet {

    /**
     * mySet是私有的并且不会逸出, 因此HashSet被封闭在PersonSet中
     */
    @GuardedBy("this") private final Set<Person> mySet = new HashSet<Person>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }

    interface Person {
    }
}
