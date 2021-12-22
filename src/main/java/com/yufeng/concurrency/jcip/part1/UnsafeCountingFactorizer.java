package com.yufeng.concurrency.jcip.part1;

import com.yufeng.concurrency.jcip.annotations.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @description
 *      1. ++count 是一个"读取-修改-写入"的操作序列
 *      2. 竞态条件(race condition): 在并发编程中, 由于不恰当的执行时序而出现不正确的结果
 *      3. 常见的竞态条件有2种:
 *         3-1 读取-修改-写入(read-modify-write)
 *         3-2 先检查后执行(check-then-act)
 * @author yufeng
 * @create 2020-04-10
 */
@NotThreadSafe
public class UnsafeCountingFactorizer extends GenericServlet implements Servlet {

    private long count = 0;

    public long getCount() {
        return count;
    }


    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(i);
        /** 非原子操作 */
        ++count;
        encodeIntoResponse(servletResponse, factors);
    }


    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {
    }


    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }


    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[] { i };
    }
}
