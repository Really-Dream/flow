package com.dream.bpm.model.controller;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dream
 * 2018/1/16.
 * 流程运行
 */
@Controller
@RequestMapping("flow")
public class FlowController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    /**
     * 可启动流程列表
     */
    @RequestMapping("index")
    public String index(Model model){
        List<Map<String,String>> listS = new ArrayList<>();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for(ProcessDefinition processDefinition : list){
            Map<String,String> map = new HashMap<>();
            map.put("name",processDefinition.getName());
            map.put("key",processDefinition.getKey());
            if(listS.contains(map)){
                continue;
            }
            listS.add(map);
        }
        model.addAttribute("list",listS);
        return "model/flow";
    }

    /**
     * 进入流程新建页面
     * @param procDefKey 流程定义Key
     */
    @RequestMapping("startIndex")
    public String startIndex(String procDefKey){
        if("BatchOnline".equals(procDefKey)){
            return "flow/BatchOnline/BatchOnline";
        }
        return "";
    }

    @RequestMapping("start")
    public String start(String procDefKey){
        runtimeService.startProcessInstanceByKey(procDefKey);
        return "";
    }
}
