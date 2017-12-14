package com.dream.util;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dream
 * 2017/12/14.
 * 将spring data jpa 的数据转化为layui的数据格式
 */
public  class Convert2Page {

    public static Map getPage(Page page){
        Map<String,Object> map = new HashMap<>();
        map.put("data",page.getContent());
        map.put("count",page.getTotalElements());
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    public static Map getPage(List list,long count){
        Map<String,Object> map = new HashMap<>();
        map.put("data",list);
        map.put("count",count);
        map.put("code",0);
        map.put("msg","");
        return map;
    }
}
