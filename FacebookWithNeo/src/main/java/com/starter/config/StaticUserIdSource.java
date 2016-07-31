package com.starter.config;


import org.springframework.social.UserIdSource;

/**
 * Created by marten on 14-07-14.
 */
public class StaticUserIdSource implements UserIdSource {
    @Override
    public String getUserId() {
        return "anonymous";
    }
}
