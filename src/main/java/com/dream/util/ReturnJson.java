package com.dream.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dream
 * 2018/1/26.
 */
public class ReturnJson {

    public static Map<String,Object> SUCCESS(){
        return SUCCESS("");
    }

    public static Map<String,Object> SUCCESS(String referer){
        return SUCCESS("提交成功",referer);
    }

    public static Map<String,Object> SUCCESS(String message,String referer){
        Map<String,Object> map = new HashMap<>();
        map.put("message",message);
        map.put("referer",referer);
        map.put("refresh",true);
        map.put("state","success");
        return map;
    }

    public static Map<String,Object> ERROR(){
        Map<String,Object> map = new HashMap<>();
        map.put("message","提交失败");
        map.put("referer","");
        map.put("refresh",false);
        map.put("state","error");
        return map;
    }

}
