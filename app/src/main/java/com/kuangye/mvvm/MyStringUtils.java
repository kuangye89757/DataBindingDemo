package com.kuangye.mvvm;

/**
 * Created by wangshijie on 2016/8/30.
 */
public class MyStringUtils  {
    public static String capitalize(final String word) {
        if (word.length() > 1) {
            return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
        }
        return word;
    }
}
