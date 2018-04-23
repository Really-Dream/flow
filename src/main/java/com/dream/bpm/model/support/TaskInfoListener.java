package com.dream.bpm.model.support;

import com.dream.bpm.model.entity.TaskInfo;
import com.dream.bpm.model.serviceImpl.TaskInfoServiceImpl;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by Dream
 * 2018/2/6.
 */
public class TaskInfoListener implements TaskListener{

    @Autowired
    TaskInfoServiceImpl service;

    @Override
    public void notify(DelegateTask delegateTask) {
        if("create".equals(delegateTask.getEventName())){
            TaskInfo taskInfo = new TaskInfo();
            BeanUtils.copyProperties(delegateTask,taskInfo);
            Map<String,Object> map = delegateTask.getVariables();
            taskInfo.setBusinessKey((String)map.get("businessKey"));
            taskInfo.setWfinstname((String)map.get("wfinstname"));
            taskInfo.setCompleteType((String)map.get(""));
            taskInfo.setNextCode((String)map.get(""));
            taskInfo.setStatus("active");
            service.save(taskInfo);
        }else if("complete".equals(delegateTask.getEventName())){
            TaskInfo taskInfo = service.findById(delegateTask.getId());
            BeanUtils.copyProperties(delegateTask,taskInfo);
            System.out.println();
        }
    }
}
