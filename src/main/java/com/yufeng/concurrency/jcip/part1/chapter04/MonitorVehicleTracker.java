package com.yufeng.concurrency.jcip.part1.chapter04;

import com.yufeng.concurrency.jcip.annotations.GuardedBy;
import com.yufeng.concurrency.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 *      基于监视器模式的车辆追踪
 * @author yufeng
 * @create 2020-04-27
 */
@ThreadSafe
public class MonitorVehicleTracker {

    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such ID: " + id);
        }
        loc.x = x;
        loc.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap<>();

        for (String id : m.keySet()) {
            result.put(id, new MutablePoint(m.get(id)));
        }

        return Collections.unmodifiableMap(result);
    }

    /**
     * 虽然MutablePoint不是线程安全的, 但追踪器类是线程安全的。它所包含的Map对象
     * 和可变的Point对象都未曾发布。当需要返回车辆的位置时, 通过MutablePoint拷贝
     * 构造函数或者deepCopy方法来复制正确的值, 从而生成一个新的Map对象, 并且该对
     * 象的值与原有Map对象中的key值和value值都相同
     */
}
