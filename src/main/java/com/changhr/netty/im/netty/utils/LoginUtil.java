package com.changhr.netty.im.netty.utils;

import com.changhr.netty.im.netty.handler.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author changhr
 * @create 2019-07-12 9:35
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
