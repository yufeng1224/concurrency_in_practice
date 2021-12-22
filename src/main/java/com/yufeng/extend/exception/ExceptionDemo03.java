package com.yufeng.extend.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @description
 *      异常代码演示(三)
 * @author yufeng
 * @create 2020-02-18
 */
public class ExceptionDemo03 {

    public static void main(String[] args) {
        System.out.println(readFile());
    }


    public static String readFile() {
        try {
            FileInputStream inputStream = new FileInputStream("/yufeng.txt");
            int ch = inputStream.read();
            System.out.println(ch);
            return "step1";
        } catch (FileNotFoundException e) {
            System.out.println("file not found exception");
            return "step2";
        } catch (IOException e) {
            System.out.println("io exception");
            return "step3";
        } finally {
            System.out.println("finally block");
            // return "finally";                        // 避免这样使用, 会覆盖前面的return返回值
        }
    }

}
