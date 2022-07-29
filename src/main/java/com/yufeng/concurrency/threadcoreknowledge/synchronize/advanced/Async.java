package com.yufeng.concurrency.threadcoreknowledge.synchronize.advanced;


import com.yufeng.concurrency.util.Constants;
import com.yufeng.concurrency.util.FileReader;
import lombok.extern.slf4j.Slf4j;

/**
 * @description
 *      异步非等待案例演示
 * @author yufeng
 * @create 2020-07-01
 */
@Slf4j(topic = "c.Async")
public class Async {

    public static void main(String[] args) {
        new Thread(() -> FileReader.read(Constants.MP4_FULL_PATH)).start();
        log.debug("do other things ...");
    }

}
