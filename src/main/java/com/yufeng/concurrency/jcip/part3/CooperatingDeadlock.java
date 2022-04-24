package com.yufeng.concurrency.jcip.part3;

import com.yufeng.concurrency.jcip.annotations.GuardedBy;

import java.util.HashSet;
import java.util.Set;

/**
 * @description
 *      相互协作对象之间的锁顺序死锁情况演示
 *          1. 调用 setLocation() 函数的线程首先会获得 Taxi对象的锁, 然后获取 Dispatcher 对象锁
 *          2. 调用 getImage() 函数的线程首先会获取 Dispatcher 对象锁, 然后获取 Taxi对象锁
 *          3. 当前情况与 LeftRightDeadlock中的情况相同: 两个线程按照不同的顺序来获取两个锁, 可能产证死锁
 *          4. 总结: 如果持有锁的情况下调用某个外部方法, 需要警惕死锁!
 * @author yufeng
 * @create 2020-03-06
 */
public class CooperatingDeadlock {

    class Taxi{

        @GuardedBy("this")
        private Point location, destination;

        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        public synchronized void setLocation(Point location) {
            this.location = location;
            if (location.equals(destination)) {
                dispatcher.notifyAvailable(this);
            }
        }

        public synchronized Point getDestination() {
            return destination;
        }

        public synchronized void setDestination(Point destination) {
            this.destination = destination;
        }
    }

    class Dispatcher {

        @GuardedBy("this")
        private final Set<Taxi> taxis;

        @GuardedBy("this")
        private final Set<Taxi> availableTaxis;

        public Dispatcher() {
            taxis = new HashSet<>();
            availableTaxis = new HashSet<>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }

        public synchronized Image getImage() {
            Image image = new Image();
            for (Taxi t : taxis) {
                image.drawMarker(t.getLocation());
            }
            return image;
        }
    }

    class Image {
        public void drawMarker(Point p) {

        }
    }
}
