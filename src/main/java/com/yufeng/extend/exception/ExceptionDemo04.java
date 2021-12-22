package com.yufeng.extend.exception;

/**
 *@description
 *      异常代码演示
 *         1. public String getMessage(): 获取异常的信息, 返回的是字符串
 *         2. public String toString(): 获取简单的异常信息描述, 包括类名和异常信息
 *         3. public void printStackTrace(): 获取异常类名和异常信息, 以及异常出现在程序中的位置, 把信息输出在控制台
 *         4. public void printStackTrace(PrintStream s): 通常用该方法将异常内容保存在日志文件中, 以便查阅
 * @author yufeng
 * @create 2020-02-18
 */
public class ExceptionDemo04 {

    public static void main(String[] args) {
        try {
            System.out.println(10/0);
        } catch (ArithmeticException e) {
            /**  / by zero */
            System.out.println(e.getMessage());

            /** java.lang.ArithmeticException: / by zero */
            System.out.println(e.toString());

            /**
             * java.lang.ArithmeticException: / by zero
             * 	at com.yufeng.extend.exception.ExceptionDemo04.main(ExceptionDemo04.java:14)
             */
            e.printStackTrace();
        }
    }
}
