package com.dream.basic.manage.controller;

import com.dream.basic.manage.dto.TbMenuDTO;
import com.dream.basic.manage.serviceImpl.TbMenuServiceImpl;
import com.dream.util.ReturnJson;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @Autowired
    Gson gson;

    @RequestMapping("list")
    @ResponseBody
    public List<TbMenuDTO> list(){
        return tbMenuService.getMenu();
    }

    /**
     * 首页
     */
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("list",tbMenuService.getMenu());
        return "index";
    }

    /**
     * 登录界面
     */
    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("logout")
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    {
        Assert.notNull(request, "HttpServletRequest required");
//        if (this.invalidateHttpSession) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate(); //使当前会话失效
            }
//        }

        SecurityContextHolder.clearContext(); //清空安全上下文
        return gson.toJson(ReturnJson.SUCCESS("/"));
    }
}
