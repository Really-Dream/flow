package com.dream.bpm.model.controller;

import com.dream.bpm.model.serviceImpl.ModelService;
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
 * 模型控制类
 */
@Controller
@RequestMapping("/bpm/model")
public class ModelController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    ModelService modelService;

    @Autowired
    Gson gson;


    /**
     * 首页
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    /**
     * model列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map list(){
        List<Model> list = repositoryService.createModelQuery().orderByCreateTime().desc().list();
        return Convert2Page.getPage(list,list.size());
    }

    /**
     * 部署
     * @param modelId modelID
     */
    @RequestMapping("deploy")
    @ResponseBody
    public String deploy(String modelId){
        try {
            modelService.deploy(modelId);
            return gson.toJson("部署成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson("部署失败！");
        }
    }

    /**
     * 删除模型
     * @param modelId modelID
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delete(String modelId){
        try{
            repositoryService.deleteModel(modelId);
        }catch (Exception e){
            e.printStackTrace();
            return gson.toJson("删除失败！");
        }
        return gson.toJson("删除成功！");
    }

}
