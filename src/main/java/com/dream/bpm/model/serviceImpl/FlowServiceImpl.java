package com.dream.bpm.model.serviceImpl;

import com.dream.util.KeyUtil;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Dream
 * 2018/1/16.
 */
@Service
public class FlowServiceImpl {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;

    public String startProcess(String proDefKey,String businessKey,Map<String,Object> map){
        //设置流程启动人
        identityService.setAuthenticatedUserId("zhangsan");
        //根据流程定义Key部署最新的流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(proDefKey,businessKey,map);
        return processInstance.getProcessInstanceId();
    }

    public String complete(String taskId, Map<String,Object> map){
        taskService.complete(taskId,map);
        return null;
    }
}
