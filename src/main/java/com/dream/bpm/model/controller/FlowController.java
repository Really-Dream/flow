package com.dream.bpm.model.controller;

import com.dream.bpm.model.entity.TaskInfo;
import com.dream.bpm.model.serviceImpl.FlowServiceImpl;
import com.dream.bpm.model.serviceImpl.InstanceAttrServiceImpl;
import com.dream.bpm.model.serviceImpl.TaskInfoServiceImpl;
import com.dream.util.KeyUtil;
import com.google.gson.Gson;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    HistoryService historyService;

    @Autowired
    TaskInfoServiceImpl taskInfoService;

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
     * 进入待办页面
     * @param procDefKey
     * @return
     */
    @RequestMapping("myTodoIndex")
    public String myTodoIndex(String procDefKey,String processInstanceId,Model model){
        List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
        for(HistoricVariableInstance historicVariableInstance : list){
            model.addAttribute(historicVariableInstance.getVariableName(),historicVariableInstance.getValue());
        }
        return "flow/"+procDefKey+"/"+procDefKey+"All";
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
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        parameterMap.put("createUser",userDetails.getUsername());

        String procDefKey = (String)parameterMap.get("procDefKey");
        String businessKey = (String)parameterMap.get("businessKey");

        if("submit".equals(parameterMap.get("type"))){
            //流程启动
            flowService.startProcess(procDefKey,businessKey,parameterMap);
        }else if("saved".equals(parameterMap.get("type"))){
            //草稿
        }
        //数据存储
//        instanceAttrService.save(parameterMap,businessKey);
        return gson.toJson("成功！");
    }

    /**
     * 任务提交
     * @param param
     */
    @RequestMapping("complete")
    @ResponseBody
    public String complete(String param){
        //JSON格式数据转化为MAP
        Map<String,Object> parameterMap = gson.fromJson(param, Map.class);

        String taskId = (String)parameterMap.get("taskId");

        flowService.complete(taskId,parameterMap);
        return gson.toJson("成功！");
    }

    /**
     * 我的待办
     */
    @RequestMapping("myTodo")
    public String myTodo(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TaskInfo> taskInfos = taskInfoService.findActive(userDetails.getUsername(),"active");
        model.addAttribute("list",taskInfos);
        return "flow/base/myTodo";
    }

    /**
     * 我的已办
     */
    @RequestMapping("myDone")
    public String myDone(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().taskAssignee(userDetails.getUsername()).list();
        model.addAttribute("list",list);
        return "flow/base/myDone";
    }

}
