package com.changhr.netty.im.netty.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author changhr
 * @create 2019-07-12 14:31
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserSession {

    private String userId;

    private String userName;

    @Override
    public String toString() {
        return userId + " : " + userName;
    }
}
