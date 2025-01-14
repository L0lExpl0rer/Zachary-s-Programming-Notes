package com.zachary.service.impl;

import com.zachary.utils.PhoneUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PhoneQueryServiceImplTest {

    @Test
    public void testGetPhoneLocation() {
        long phone = 18260515064L;
        int country = 86;
        System.out.println(PhoneUtils.getPhoneLocation(phone, country));
    }

    @Test
    public void testGetPhoneOperator() {
        long phone = 18260515064L;
        int country = 86;
        System.out.println(PhoneUtils.getPhoneOperator(phone, country));
    }

    @Test
    public void testGetPhoneOperator1() {
        String head = "182xx";
        System.out.println(PhoneUtils.getPhoneOperator(head));
    }

    @Test
    public void testGetPhoneDetails() {
        long phone = 18260515064L;
        int country = 86;
        System.out.println(PhoneUtils.getPhoneDetails(phone, country));
    }

    @Test
    public void testIsValid() {
        long phone = 18260515064L;
        int country = 86;
        System.out.println(PhoneUtils.isValid(phone, country));
    }


}
