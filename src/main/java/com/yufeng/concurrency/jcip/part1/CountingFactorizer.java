package com.yufeng.concurrency.jcip.part1;

import javax.servlet.*;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @description
 *      1. 使用AtomicLong类型的变量来统计已处理请求的数量, 可以确保线程安全
 *      2. UnsafeCountingFactorizer类的改进版
 * @author yufeng
 * @create 2020-04-21
 */
public class CountingFactorizer extends GenericServlet implements Servlet {

    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(servletResponse, factors);
    }

    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {}

    BigInteger extractFromRequest(ServletRequest req) {
        return null;
    }

    BigInteger[] factor(BigInteger i) {
        return null;
    }
}
