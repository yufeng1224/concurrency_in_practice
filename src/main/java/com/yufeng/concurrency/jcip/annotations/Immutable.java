package com.yufeng.concurrency.jcip.annotations;

import java.lang.annotation.*;

/**
 * The class to which this annotation is applied is com.yufeng.concurrency.juc.immutable. This means that
 * its state cannot be seen to change by callers, which implies that
 * <ul>
 *  <li>all public fields are final, </li>
 *  <li>all public final reference fields refer to other com.yufeng.concurrency.juc.immutable objects, and</li>
 *  <li>constructors and methods do not publish references to any internal state
 *      which is potentially mutable by the implementation.</li>
 * </ul>
 * Immutable objects may still have internal mutable state for purpose of performance
 * optimization; some state variables may be lazily computed, so long as they are computed
 * from com.yufeng.concurrency.juc.immutable state and that callers cannot tell the difference.
 * <p>
 * Immutable objects are inherently thread-safe; they may be passed between threads or
 * published without synchronization.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Immutable {
}






















