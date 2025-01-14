package com.zachary.utils;

import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import com.zachary.constant.PhoneConstant;

import java.util.Locale;

public class PhoneUtils {

    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    private static final PhoneNumberToCarrierMapper carrierMapper = PhoneNumberToCarrierMapper.getInstance();
    private static final PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();

    /**
     * 获取电话号码的地理位置，用中文表示
     *
     * @param phone   电话号码
     * @param country 国家代码
     * @return 电话号码的地理位置，用中文表示
     */
    public static String getPhoneLocation(Long phone, int country) {
        Phonenumber.PhoneNumber phoneNumber = getPhoneNumber(phone, country); // 获取电话号码
        return getLocation(phoneNumber); // 获取电话号码的地理位置，用中国语言环境表示
    }

    /**
     * 获取电话号码运营商，用中文表示
     *
     * @param phone   电话号码
     * @param country 国家代码
     * @return 电话号码运营商，用中文表示
     */
    public static String getPhoneOperator(Long phone, int country) {
        Phonenumber.PhoneNumber phoneNumber = getPhoneNumber(phone, country); // 获取电话号码
        return getOperator(phoneNumber); // 获取电话号码的地理位置，用中国语言环境表示
    }

    /**
     * 获取电话号码运营商，用中文表示
     *
     * @param head 电话号码前若干位
     * @return 电话号码运营商，用中文表示
     */
    public static String getPhoneOperator(String head) {
        if (head.startsWith("134") || head.startsWith("135") || head.startsWith("136")
                || head.startsWith("137") || head.startsWith("138") || head.startsWith("139")
                || head.startsWith("147") || head.startsWith("148") || head.startsWith("150")
                || head.startsWith("151") || head.startsWith("152") || head.startsWith("157")
                || head.startsWith("158") || head.startsWith("159") || head.startsWith("178")
                || head.startsWith("182") || head.startsWith("183") || head.startsWith("184")
                || head.startsWith("187") || head.startsWith("188") || head.startsWith("198")) {
            return PhoneConstant.CHINA_MOBILE_CN;
        } else if (head.startsWith("130") || head.startsWith("131") || head.startsWith("132")
                || head.startsWith("145") || head.startsWith("155") || head.startsWith("156")
                || head.startsWith("166") || head.startsWith("171") || head.startsWith("175")
                || head.startsWith("176") || head.startsWith("185") || head.startsWith("186")) {
            return PhoneConstant.CHINA_UNICOM_CN;
        } else if (head.startsWith("133") || head.startsWith("149") || head.startsWith("153")
                || head.startsWith("173") || head.startsWith("177") || head.startsWith("180")
                || head.startsWith("181") || head.startsWith("189") || head.startsWith("191")
                || head.startsWith("193") || head.startsWith("199")) {
            return PhoneConstant.CHINA_TELECOM_CN;
        } else {
            return PhoneConstant.UNKNOWN;
        }
    }

    /**
     * 获取电话号码的详细些信息 = 地理位置 + 运营商，用中文表示
     *
     * @param phone   电话号码
     * @param country 国家代码
     * @return 电话号码的详细些信息 = 地理位置 + 运营商，用中文表示
     */
    public static String getPhoneDetails(Long phone, int country) {
        Phonenumber.PhoneNumber phoneNumber = getPhoneNumber(phone, country); // 获取电话号码
        return getLocation(phoneNumber) + getOperator(phoneNumber); // 获取电话号码的地理位置，用中国语言环境表示
    }

    /**
     * 判断是否是有效电话号码
     *
     * @param phone   电话号码
     * @param country 国家代码
     * @return true：有效电话号码；false：无效电话号码
     */
    public static boolean isValid(Long phone, int country) {
        Phonenumber.PhoneNumber phoneNumber = getPhoneNumber(phone, country); // 获取电话号码
        return phoneNumberUtil.isValidNumber(phoneNumber);
    }

    /**
     * 封装电话号码对象
     *
     * @param phone   电话号码
     * @param country 国家代码
     * @return 电话号码对象
     */
    private static Phonenumber.PhoneNumber getPhoneNumber(Long phone, int country) {
        Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
        phoneNumber.setCountryCode(country);
        phoneNumber.setNationalNumber(phone);
        return phoneNumber;
    }

    /**
     * 获取电话号码地理位置，中文
     *
     * @param phoneNumber 电话号码对象
     * @return 地理位置
     */
    private static String getLocation(Phonenumber.PhoneNumber phoneNumber) {
        return geocoder.getDescriptionForNumber(phoneNumber, Locale.CHINA);
    }

    /**
     * 获取电话号码运营商，中文
     *
     * @param phoneNumber 电话号码对象
     * @return 运营商
     */
    private static String getOperator(Phonenumber.PhoneNumber phoneNumber) {
        String operator = carrierMapper.getNameForNumber(phoneNumber, Locale.ENGLISH);
        switch (operator) {
            case (PhoneConstant.CHINA_MOBILE):
                return PhoneConstant.CHINA_MOBILE_CN;
            case (PhoneConstant.CHINA_UNICOM):
                return PhoneConstant.CHINA_UNICOM_CN;
            case (PhoneConstant.CHINA_TELECOM):
                return PhoneConstant.CHINA_TELECOM_CN;
            default:
                return PhoneConstant.UNKNOWN;
        }
    }
}
