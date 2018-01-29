package com.dream.bpm.model.controller;

import com.dream.bpm.model.entity.TbNode;
import com.dream.bpm.model.serviceImpl.DeployedService;
import com.dream.bpm.model.serviceImpl.TbNodeServiceImpl;
import com.dream.util.ReturnJson;
import com.google.gson.Gson;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dream
 * 2017/12/19.
 * 已部署模型相关操作
 */
@Controller
@RequestMapping("bpm/deployed")
public class DeployedController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    DeployedService deployedService;

    @Autowired
    TbNodeServiceImpl tbNodeService;

    @Autowired
    Gson gson;

    /**
     * 已部署模型列表
     */
    @RequestMapping("index")
    public String index(Model model){
        List<Map<String,String>> listS = new ArrayList<>();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().asc().list();
        for(ProcessDefinition processDefinition : list){
            Map<String,String> map = new HashMap<>();
            map.put("ID",processDefinition.getId());
            map.put("name",processDefinition.getName());
            map.put("deploymentId",processDefinition.getDeploymentId());
            listS.add(map);
        }
        model.addAttribute("list",listS);
        return "model/deployed";
    }

    /**
     * 删除已部署模型
     * @param deploymentId 模型部署ID
     * @param procDefId 流程定义ID
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delete(String deploymentId,String procDefId){
        try{
            deployedService.delete(deploymentId,procDefId);
            return gson.toJson(ReturnJson.SUCCESS());
        }catch (Exception e){
            e.printStackTrace();
            return gson.toJson(ReturnJson.ERROR());
        }
    }

    /**
     * 已部署模型的节点列表
     * @param procDefId 流程定义ID
     */
    @RequestMapping("nodeIndex")
    public String nodeIndex(String procDefId,Model model){
        List<TbNode> list = tbNodeService.findAllByProcDefId(procDefId);
        model.addAttribute("list",list);
        model.addAttribute("procDefId",procDefId);
        return "model/nodeList";
    }
}
