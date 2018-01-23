package com.dream.basic.manage.controller;

import com.dream.basic.manage.dto.TbMenuDTO;
import com.dream.basic.manage.serviceImpl.TbMenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        return "index";
    }
}
