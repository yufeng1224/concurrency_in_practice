package com.yufeng.concurrency.jcip.part3;

import com.yufeng.concurrency.jcip.annotations.Immutable;

/**
 * @description
 *      
 * @author yufeng
 * @create 2020-03-06
 */
@Immutable
public class Point {

    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
