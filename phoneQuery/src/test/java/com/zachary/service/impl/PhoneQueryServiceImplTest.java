package com.zachary.service.impl;

import com.zachary.service.PhoneQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PhoneQueryServiceImplTest {
    @Autowired
    private PhoneQueryService phoneQueryService;

    @Test
    public void testGetPhoneLocation() {
        System.out.println(phoneQueryService.getPhoneLocation("13485182568", "86"));
    }

    @Test
    public void testGetPhoneOperator() {
        System.out.println(phoneQueryService.getPhoneOperator("13485182568", "86"));
    }

    @Test
    public void testGetPhoneDetails() {
        System.out.println(phoneQueryService.getPhoneDetails("13485182568", "86"));
    }

    @Test
    public void testIsValid() {
        System.out.println(phoneQueryService.isValid("13485182568", "86"));
    }

    @Test
    public void testGetPhoneOperator2() {
        System.out.println(phoneQueryService.getPhoneOperator("134XX"));
    }
}
