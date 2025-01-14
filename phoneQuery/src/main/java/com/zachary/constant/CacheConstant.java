package com.zachary.constant;


import java.util.concurrent.TimeUnit;

public class CacheConstant {
    public static final long TTL = 60; // 缓存过期时间
    public static final TimeUnit TTL_UNIT = TimeUnit.MINUTES; // 缓存过期时间单位
    public static final String REDIS_KEY_PREFIX = "phone_info:"; // 键开头值
}


