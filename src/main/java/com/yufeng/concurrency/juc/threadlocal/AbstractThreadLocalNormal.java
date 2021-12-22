package com.yufeng.concurrency.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description
 *      抽象基类
 * @author yufeng
 * @create 2020-03-15
 */
public abstract class AbstractThreadLocalNormal {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public String date(int seconds) {
        /** 参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时 */
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return dateFormat.format(date);
    }

}
