package com.zachary.controller;

import com.zachary.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/phone")
public class PhoneQueryController {

    @GetMapping("/operator")
    public Result<String> queryPhoneOperator(String phoneHead) {
        return null;
    }

    @GetMapping("/location")
    public Result<String> queryPhoneLocation(String phone, String country) {
        return null;
    }

    @GetMapping("/details")
    public Result<String> queryPhoneDetails(String phone, String country) {
        return null;
    }


}
