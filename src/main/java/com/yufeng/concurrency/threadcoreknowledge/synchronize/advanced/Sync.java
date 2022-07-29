package com.yufeng.concurrency.threadcoreknowledge.synchronize.advanced;

import com.yufeng.concurrency.util.Constants;
import com.yufeng.concurrency.util.FileReader;
import lombok.extern.slf4j.Slf4j;

/**
 * @description
 *      同步等待案例演示
 * @author yufeng
 * @create 2020-07-01
 */
@Slf4j(topic = "c.Sync")
public class Sync {

    public static void main(String[] args) {
        FileReader.read(Constants.MP4_FULL_PATH);
        log.debug("do other things ...");
    }

}
