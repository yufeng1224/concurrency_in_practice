package com.yufeng.concurrency.jcip.part1.chapter02;

import com.yufeng.concurrency.jcip.annotations.NotThreadSafe;

import javax.servlet.*;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description
 *      1. 该Servlet没有对计算结果进行原子性保证(非线程安全)
 *      2. 要保持状态的一致性, 就需要在单个原子操作中更新所有相关的状态变量
 * @author yufeng
 * @create 2020-04-21
 */
@NotThreadSafe
public class UnsafeCachingFactorizer extends GenericServlet implements Servlet {

    private final AtomicReference<BigInteger> lastNumber
            = new AtomicReference<BigInteger>();

    private final AtomicReference<BigInteger[]> lastFactors
            = new AtomicReference<BigInteger[]>();

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        BigInteger i = extractFromRequest(servletRequest);
        if (i.equals(lastNumber.get()))
            encodeIntoResponse(servletResponse, lastFactors.get());
        else {
            BigInteger[] factors = factor(i);
            /** 组合操作是非原子性的操作, 无法保证线程安全 */
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoResponse(servletResponse, factors);
        }
    }

    void encodeIntoResponse(ServletResponse servletResponse, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest servletRequest) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }

}
