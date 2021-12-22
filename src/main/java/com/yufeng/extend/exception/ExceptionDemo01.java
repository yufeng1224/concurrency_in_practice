package com.yufeng.extend.exception;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @description
 *      异常代码演示(一)
 * @author yufeng
 * @create 2020-02-18
 */
public class ExceptionDemo01 {

    public static void main(String[] args) {
        try {
            createFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void createFile() throws IOException {
        File file = new File("/yufeng.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
    }

}
