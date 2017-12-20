package com.dream.util;

import java.util.List;

/**
 * Created by Dream
 * 2017/12/20.
 */
public class CheckNull {

    /**
     * 检查List，为null或者空，返回false
     */
    public static boolean checkList(List list){
        if(list == null || list.size() <= 0){
            return false;
        }else{
            return true;
        }
    }
}
