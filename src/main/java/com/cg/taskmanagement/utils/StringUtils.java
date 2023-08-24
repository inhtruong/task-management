package com.cg.taskmanagement.utils;

public class StringUtils {

    public static boolean isEmpty(Object obj) {
        if (nullOrBlank(obj)) {
            return true;
        } else {
            String s = obj.toString();
            s=s.replaceAll("[ 　]", "");
            return nullOrBlank(s);
        }
    }

    public static boolean nullOrBlank(Object obj) {
        if (obj == null) {
            return true;
        }
        if ("".equals(obj.toString())) {
            return true;
        }
        return false;
    }
}
