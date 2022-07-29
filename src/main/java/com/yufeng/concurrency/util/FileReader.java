package com.yufeng.concurrency.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @description
 *      读取文件工具类
 * @author yufeng
 * @create 2020-07-01
 */
@Slf4j(topic = "c.FileReader")
public class FileReader {

    public static void read(String filename) {

        int idx = filename.lastIndexOf(File.separator);
        String shortName = filename.substring(idx + 1);

        try (FileInputStream in = new FileInputStream(filename)) {
            long start = System.currentTimeMillis();
            log.debug("read [{}] start ...", shortName);
            byte[] buf = new byte[1024];
            int n = -1;
            do {
                n = in.read(buf);
            } while (n != -1);
            long end = System.currentTimeMillis();
            log.debug("read [{}] end ... cost: {} ms", shortName, end - start);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}