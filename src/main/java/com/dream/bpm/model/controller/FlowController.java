package com.dream.bpm.model.controller;

import com.dream.bpm.model.serviceImpl.FlowServiceImpl;
import com.dream.bpm.model.serviceImpl.InstanceAttrServiceImpl;
import com.dream.util.KeyUtil;
import com.google.gson.Gson;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
    FlowServiceImpl flowService;

    @Autowired
    InstanceAttrServiceImpl instanceAttrService;

    @Autowired
    Gson gson;

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
    public String startIndex(String procDefKey,Model model){
        model.addAttribute("procDefKey",procDefKey);
        model.addAttribute("businessKey", KeyUtil.getUniqueKey());
        if("BatchOnline".equals(procDefKey)){
            return "flow/BatchOnline/BatchOnline";
        }
        return "flow/"+procDefKey+"/"+procDefKey;
    }

    /**
     * 流程启动
     * @param param 业务数据
     */
    @RequestMapping("start")
    @ResponseBody
    public String start(String param){
        //JSON格式数据转化为MAP
        Map<String,Object> parameterMap = gson.fromJson(param, Map.class);
        //添加第一步处理人
        parameterMap.put("createUser","admin");

        String procDefKey = (String)parameterMap.get("procDefKey");
        String businessKey = (String)parameterMap.get("businessKey");
        //流程启动
        flowService.startProcess(procDefKey,businessKey,parameterMap);
        //数据存储
        instanceAttrService.save(parameterMap,businessKey);
        return gson.toJson("成功！");
    }
}
