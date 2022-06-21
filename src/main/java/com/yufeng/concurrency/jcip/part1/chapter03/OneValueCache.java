package com.yufeng.concurrency.jcip.part1.chapter03;

import com.yufeng.concurrency.jcip.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @description
 *      对数值及其因数分解结果进行缓存的不可变容器类
 * @author yufeng
 * @create 2020-04-25
 */
@Immutable
public class OneValueCache {

    private final BigInteger lastNumber;

    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i, BigInteger[] factors) {
        lastNumber = i;
        lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i)) {
            return null;
        }
        return Arrays.copyOf(lastFactors, lastFactors.length);
    }

}
