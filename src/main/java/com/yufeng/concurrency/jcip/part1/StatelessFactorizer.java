package com.yufeng.concurrency.jcip.part1;

import com.yufeng.concurrency.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.math.BigInteger;

/**
 * @description
 *        1. 与大多数Servlet相同, StatelessFactorizer是无状态的: 它既不包含任何域, 也不包含任何对
 *           其他类中域的引用
 *        2. 计算过程中的临时状态仅存在于线程栈的局部变量中, 并且只能由正在执行的线程访问
 *        3. 访问StatelessFactorizer的线程不会影响另一个访问同一个StatelessFactorizer的线程的计算结果,
 *           因为这两个线程并没有共享状态, 就好像它们都在访问不同的实例
 *        4. 线程访问无状态对象的行为并不会影响其他线程中操作的正确性, 因此无状态对象一定是线程安全的
 * @author yufeng
 * @create 2020-04-10
 */
@ThreadSafe
public class StatelessFactorizer extends GenericServlet implements Servlet {

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(servletResponse, factors);
    }


    void encodeIntoResponse(ServletResponse response, BigInteger[] factors) {
    }


    BigInteger extractFromRequest(ServletRequest request) {
        return new BigInteger("7");
    }


    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}
