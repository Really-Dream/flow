package com.dream.basic.manage.controller;

import com.dream.basic.manage.dto.TbMenuDTO;
import com.dream.basic.manage.serviceImpl.TbMenuServiceImpl;
import com.dream.util.ReturnJson;
import com.google.gson.Gson;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    IdentityService identityService;

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
        //获取当前登录用户
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = identityService.createUserQuery().userId(userDetails.getUsername()).singleResult();
        model.addAttribute("user",user);
        return "index";
    }

    /**
     * 登录界面
     */
    @RequestMapping("login")
    public String login(){
        return "login";
    }

    /**
     * 退出
     */
    @RequestMapping("logout")
    @ResponseBody
    public String logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); //使当前会话失效
        }

        SecurityContextHolder.clearContext(); //清空安全上下文
        return gson.toJson(ReturnJson.SUCCESS("/"));
    }
}
