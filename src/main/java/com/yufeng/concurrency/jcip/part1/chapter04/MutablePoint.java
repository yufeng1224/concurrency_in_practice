package com.yufeng.concurrency.jcip.part1.chapter04;

import com.yufeng.concurrency.jcip.annotations.NotThreadSafe;

/**
 * @description
 *      
 * @author yufeng
 * @create 2020-04-27
 */
@NotThreadSafe
public class MutablePoint {

    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
