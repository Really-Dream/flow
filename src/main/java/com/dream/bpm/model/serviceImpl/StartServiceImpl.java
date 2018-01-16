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
public class StartServiceImpl {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;

    public String startProcess(String proDefKey,Map<String,Object> map){
        //根据流程定义Key部署最新的流程
        identityService.setAuthenticatedUserId("zhangsan");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(proDefKey, KeyUtil.getUniqueKey(),map);
        return processInstance.getProcessInstanceId();
    }

    public String complete(String taskId, Map<String,Object> map){
        taskService.complete(taskId,map);
        return null;
    }
}
