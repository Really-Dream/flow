package com.dream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Dream
 * 2017/12/12.
 */
@Controller
@RequestMapping("/ftl")
public class FreemarkerController {

    @RequestMapping("/hello")
    public String hello(Map<String,Object> map){
        map.put("name","hello,worldxxx");
        return "hello";
    }
}
