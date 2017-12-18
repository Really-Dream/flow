package com.dream.util;

import java.util.Random;

/**
 * Created by Dream
 * 2017/12/18.
 */
public class KeyUtil {

    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer integer = random.nextInt(900000)+100000;

        return System.currentTimeMillis()+String.valueOf(integer);
    }
}
