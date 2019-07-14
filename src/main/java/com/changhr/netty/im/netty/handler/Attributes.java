package com.changhr.netty.im.netty.handler;

import com.changhr.netty.im.netty.session.UserSession;
import io.netty.util.AttributeKey;

/**
 * @author changhr
 * @create 2019-07-12 9:34
 */
public interface Attributes {

    AttributeKey<UserSession> SESSION = AttributeKey.newInstance("session");
}
