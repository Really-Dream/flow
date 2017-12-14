package com.dream.controller.bpm.model;

import com.dream.util.Convert2Page;
import com.google.gson.Gson;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Dream
 * 2017/12/13.
 */
@Controller
@RequestMapping("/bpm/model")
public class ModelController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    Gson gson;

    @RequestMapping("index")
    public String index(){
        return "model/modelList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map list(){
        List<Model> list = repositoryService.createModelQuery().orderByCreateTime().desc().list();
        return Convert2Page.getPage(list,list.size());
    }

    @RequestMapping("deploy")
    @ResponseBody
    public String deploy(String modelId){
        return null;
    }

    @RequestMapping("delete")
    public String delete(String modelId){
        repositoryService.deleteModel(modelId);
        return "redirect:/bpm/model/index";
    }

}
