package com.zachary.controller;

import com.zachary.result.Result;
import com.zachary.service.PhoneQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/phone")
public class PhoneQueryController {

    @Autowired
    private PhoneQueryService phoneQueryService;

    @GetMapping("/location")
    public Result<String> queryPhoneLocation(@RequestParam String phone, @RequestParam String country) {
        String location = phoneQueryService.getPhoneLocation(phone, country);
        return Result.success(location);
    }

    @GetMapping("/operator/prefix")
    public Result<String> queryPhoneOperator(@RequestParam String phonePrefix) {
        String operator = phoneQueryService.getPhoneOperator(phonePrefix);
        return Result.success(operator);
    }

    @GetMapping("/operator/{phone}/{country}")
    public Result<String> queryPhoneOperator(@PathVariable String phone, @PathVariable String country) {
        String operator = phoneQueryService.getPhoneOperator(phone, country);
        return Result.success(operator);
    }

    @GetMapping("/details")
    public Result<String> queryPhoneDetails(@RequestParam String phone, @RequestParam String country) {
        String details = phoneQueryService.getPhoneDetails(phone, country);
        return Result.success(details);
    }

    @GetMapping("/valid")
    public Result<Boolean> isValid(@RequestParam String phone, @RequestParam String country) {
        Boolean isValid = phoneQueryService.isValid(phone, country);
        return Result.success(isValid);
    }
}
