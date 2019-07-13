package com.changhr.netty.im.netty.utils;

import java.util.UUID;

/**
 * @author changhr2013
 * @date 2019/7/14
 */
public class IDUtil {

    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
