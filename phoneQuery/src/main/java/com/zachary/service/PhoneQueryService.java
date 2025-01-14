package com.zachary.service;

public interface PhoneQueryService {

    /**
     * 获取电话号码的地理位置
     *
     * @param phone   电话号码
     * @param country 国家代码
     * @return 电话号码的地理位置
     */
    String getPhoneLocation(String phone, String country);

    /**
     * 获取电话号码的运营商
     *
     * @param phone   电话号码
     * @param country 国家代码
     * @return 电话号码的运营商
     */
    String getPhoneOperator(String phone, String country);

    /**
     * 获取电话号码的运营商
     *
     * @param head 电话号码前若干位
     * @return 电话号码的运营商
     */
    String getPhoneOperator(String head);

    /**
     * 获取电话号码的详细些信息 = 地理位置 + 运营商
     *
     * @param phone   电话号码
     * @param country 国家代码
     * @return 电话号码的详细些信息 = 地理位置 + 运营商
     */
    String getPhoneDetails(String phone, String country);

    /**
     * 判断电话号码是否有效
     *
     * @param phone   电话号码
     * @param country 国家代码
     * @return 电话号码是否有效
     */
    Boolean isValid(String phone, String country);
}
