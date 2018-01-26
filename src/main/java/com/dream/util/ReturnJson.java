package com.dream.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dream
 * 2018/1/26.
 */
public class ReturnJson {

    public static Map<String,Object> SUCCESS(){
        Map<String,Object> map = new HashMap<>();
        map.put("message","提交成功");
        map.put("referer","");
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
