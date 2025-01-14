package com.zachary.service.impl;

import com.zachary.constant.CacheConstant;
import com.zachary.constant.MessageConstant;
import com.zachary.service.PhoneQueryService;
import com.zachary.utils.PhoneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PhoneQueryServiceImpl implements PhoneQueryService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private PhoneQueryService phoneQueryService;

    @Override
    public String getPhoneLocation(String phone, String country) {
        if (!phoneQueryService.isValid(phone, country)) {
            return MessageConstant.INVALID_PHONE;
        }

        String redisKey = CacheConstant.REDIS_KEY_PREFIX + "location:" + country + ":" + phone;
        // 在缓存中查询
        String cacheValue = redisTemplate.opsForValue().get(redisKey);
        if (cacheValue != null) {
            return cacheValue;
        }
        // 缓存未命中，调用工具类，查询电话地址信息
        String location = PhoneUtils.getPhoneLocation(Long.parseLong(phone), Integer.parseInt(country));
        // 将结果存入缓存，设置TTL
        redisTemplate.opsForValue().set(redisKey, location, CacheConstant.TTL, CacheConstant.TTL_UNIT);
        return location;
    }

    @Override
    public String getPhoneOperator(String phone, String country) {
        if (!phoneQueryService.isValid(phone, country)) {
            return MessageConstant.INVALID_PHONE;
        }

        String redisKey = CacheConstant.REDIS_KEY_PREFIX + "operator:" + country + ":" + phone;
        // 在缓存中查询
        String cacheValue = redisTemplate.opsForValue().get(redisKey);
        if (cacheValue != null) {
            return cacheValue;
        }
        // 缓存未命中，调用工具类，查询电话运营商信息
        String operator = PhoneUtils.getPhoneOperator(Long.parseLong(phone), Integer.parseInt(country));
        // 将结果存入缓存，设置TTL
        redisTemplate.opsForValue().set(redisKey, operator, CacheConstant.TTL, CacheConstant.TTL_UNIT);
        return operator;
    }

    @Override
    public String getPhoneOperator(String head) {
        if (head.length() <3 || !head.substring(0,3).matches("\\d{3}")) {
            return MessageConstant.INVALID_PHONE;
        }

        String redisKey = CacheConstant.REDIS_KEY_PREFIX + "operator:head" + head;
        // 在缓存中查询
        String cacheValue = redisTemplate.opsForValue().get(redisKey);
        if (cacheValue != null) {
            return cacheValue;
        }
        // 缓存未命中，调用工具类，查询电话运营商信息
        String operator = PhoneUtils.getPhoneOperator(head);
        // 将结果存入缓存，设置TTL
        redisTemplate.opsForValue().set(redisKey, operator, CacheConstant.TTL, CacheConstant.TTL_UNIT);
        return operator;
    }

    @Override
    public String getPhoneDetails(String phone, String country) {
        if (!phoneQueryService.isValid(phone, country)) {
            return MessageConstant.INVALID_PHONE;
        }

        String redisKey = CacheConstant.REDIS_KEY_PREFIX + "details:" + country + ":" + phone;
        // 在缓存中查询
        String cacheValue = redisTemplate.opsForValue().get(redisKey);
        if (cacheValue != null) {
            return cacheValue;
        }
        // 缓存未命中，调用工具类，查询电话详细信息
        String details = PhoneUtils.getPhoneDetails(Long.parseLong(phone), Integer.parseInt(country));
        // 将结果存入缓存，设置TTL
        redisTemplate.opsForValue().set(redisKey, details, CacheConstant.TTL, CacheConstant.TTL_UNIT);
        return details;
    }

    @Override
    public Boolean isValid(String phone, String country) {
        String redisKey = CacheConstant.REDIS_KEY_PREFIX + "valid:" + country + ":" + phone;
        // 在缓存中查询
        String cacheValue = redisTemplate.opsForValue().get(redisKey);
        if (cacheValue != null) {
            return Boolean.parseBoolean(cacheValue);
        }
        // 缓存未命中，调用工具类，查询电话有效性
        Boolean isValid = PhoneUtils.isValid(Long.parseLong(phone), Integer.parseInt(country));
        // 将结果存入缓存，设置TTL
        redisTemplate.opsForValue().set(redisKey, isValid.toString(), CacheConstant.TTL, CacheConstant.TTL_UNIT);
        return isValid;
    }
}
