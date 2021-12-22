package com.yufeng.concurrency.juc.collections.predecessor;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 *      演示Map的常用方法
 * @author yufeng
 * @create 2020-03-26
 */
public class MapDemo {
    public static void main(String[] args) {
        Map<String, String> stringMap = new HashMap<>();

        /**1. 获取Map集合的大小 */
        System.out.println(stringMap.size());

        /**2. 判断集合是否为空 */
        System.out.println(stringMap.isEmpty());

        /**3. 添加键值对 */
        stringMap.put("大哥","32");
        stringMap.put("小弟","3");
        stringMap.put("小小弟", "1");
        stringMap.put("大师兄", "");

        /**4. 获取键的集合 */
        System.out.println(stringMap.keySet());

        /**5. 获取键对应的值  */
        System.out.println(stringMap.get("大哥"));

        /**6. 判断是否包含某个键 */
        System.out.println(stringMap.containsKey("大哥"));

        /**7. 删除对应的键值对 */
        stringMap.remove("大师兄");

    }
}
