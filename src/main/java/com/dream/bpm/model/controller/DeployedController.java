package com.dream.bpm.model.controller;

import com.dream.bpm.model.serviceImpl.DeployedService;
import com.dream.util.Convert2Page;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dream
 * 2017/12/19.
 */
@Controller
@RequestMapping("bpm/deployed")
public class DeployedController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    DeployedService deployedService;

    @RequestMapping("list")
    @ResponseBody
    public Map list(){
        List<Map<String,String>> listS = new ArrayList<>();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().asc().list();
        for(ProcessDefinition processDefinition : list){
            Map<String,String> map = new HashMap<>();
            map.put("ID",processDefinition.getId());
            map.put("name",processDefinition.getName());
            map.put("deploymentId",processDefinition.getDeploymentId());
            listS.add(map);
        }
        return Convert2Page.getPage(listS,listS.size());
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(String deploymentId,String procDefId){
        try{
            deployedService.delete(deploymentId,procDefId);
            return "删除成功";
        }catch (Exception e){
            e.printStackTrace();
            return "删除失败";
        }
    }
}
