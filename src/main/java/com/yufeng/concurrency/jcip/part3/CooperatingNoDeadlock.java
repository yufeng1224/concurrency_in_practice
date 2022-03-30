package com.yufeng.concurrency.jcip.part3;

import com.yufeng.concurrency.jcip.annotations.GuardedBy;
import com.yufeng.concurrency.jcip.annotations.ThreadSafe;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @description
 *      通过开放调用来避免在相互协作的对象之间产生死锁
 * @author yufeng
 * @create 2020-03-06
 */
public class CooperatingNoDeadlock {

    @ThreadSafe
    class Taxi {

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
            boolean reachedDestination;
            synchronized (this) {
                this.location = location;
                reachedDestination = location.equals(destination);
            }

            if (reachedDestination) {
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

        public Image getImage() {
            Set<Taxi> copy;
            synchronized (this) {
                copy = new HashSet<>(taxis);                // 创建对象的副本
            }

            Image image = new Image();
            for (Taxi t : copy) {
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
