package com.suresh.androidapp.helpers;

import java.util.HashMap;

/**
 * Created by suresh on 11/20/16.
 */

public class ValidationHelper {

    public static HashMap<Object, Object> isValidPhone(String phone) {
        String regexStr = "^[0-9\\-]*$";
        HashMap<Object, Object> hm = new HashMap<>();
        hm.put("isValidPhone", true);
        hm.put("error", "");

        boolean validPhoneLength = (phone.length() <= 14 && phone.length() > 9)  ? true : false;

        if (!validPhoneLength) {
            hm.put("isValidPhone", validPhoneLength);
            hm.put("error", "Valid phone must be at least 9 chars and not exeeeding 14 chars in length");
            return hm;
        }
        else if (validPhoneLength) {
            boolean isValidRegex = phone.matches(regexStr) ? true : false;
            if (!isValidRegex) {
                hm.put("isValidPhone", isValidRegex);
                hm.put("error", "Phone pattern do not match");
            }
            else{
                hm.put("isValidPhone", isValidRegex);
                hm.put("error", "");
            }
            return hm;
        }
        return hm;
    }

    public static HashMap<Object, Object> isValidName(String name) {

        HashMap<Object, Object> hm = new HashMap<>();
        hm.put("isValidName", true);
        hm.put("error", "");

        boolean validNameLength = (name.length() < 20 && name.length() > 1) ? true : false;

        if (!validNameLength) {
            hm.put("isValidName", validNameLength);
            hm.put("error", "Name should not exceeds more than 20 chars");
            return hm;
        }
        return hm;
    }
}
