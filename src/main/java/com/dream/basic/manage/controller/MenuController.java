package com.dream.basic.manage.controller;

import com.dream.basic.manage.dto.TbMenuDTO;
import com.dream.basic.manage.serviceImpl.TbMenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dream
 * 2017/12/20.
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    TbMenuServiceImpl tbMenuService;

    @RequestMapping("list")
    @ResponseBody
    public List<TbMenuDTO> getMenu(){
        return tbMenuService.getMenu();
    }

    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("list",tbMenuService.getMenu());
        model.addAttribute("s","kkk");
        Map<String,String> map = new HashMap<>();
        map.put("s","s");
        return "index";
    }

    @RequestMapping("table")
    public String table(){
        return "table";
    }
}
